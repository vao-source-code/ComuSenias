package com.ars.comusenias.util


import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.ars.comusenias.presentation.component.defaults.app.CircularProgressBar
import com.ars.comusenias.presentation.ui.theme.SIZE16
import com.ars.comusenias.presentation.ui.theme.SIZE8
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@OptIn(UnstableApi::class)
@Composable
fun PlayVideo(videoUrl: String, isVideoYoutube: Boolean = false) {
    if (isVideoYoutube) {
        val lifecycleOwner = LocalLifecycleOwner.current
        YoutubePlayer(videoUrl, lifecycleOwner)
    } else {
        val context = LocalContext.current
        val videoPlayer =
            remember { VideoPlayer(context, videoUrl) }
        val isVideoReady = remember { mutableStateOf(false) }
        DisposableEffect(videoPlayer) {
            val exoPlayer = videoPlayer.initializePlayer()
            exoPlayer.addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    super.onPlaybackStateChanged(state)
                    if (state == ExoPlayer.STATE_READY) {
                        isVideoReady.value = true
                    }
                }
            })
            videoPlayer.preparePlayer()
            onDispose {
                videoPlayer.releasePlayer()
            }
        }
        ShowVideo(isVideoReady, videoPlayer)
    }
}

@Composable
private fun ShowVideo(
    isVideoReady: MutableState<Boolean>,
    videoPlayer: VideoPlayer
) {
    Crossfade(targetState = isVideoReady.value, label = "") { isReady ->
        if (isReady) {
            AndroidView(
                factory = { videoPlayer.getPlayerView() },
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )
        } else {
            CircularProgressBar()
        }
    }
}


/**
 * Función composable que muestra un reproductor de video de YouTube utilizando la API AndroidView en Jetpack Compose.
 *
 * Esta función crea un YouTubePlayerView, lo inicializa con el ID del video de YouTube proporcionado
 * y maneja el ciclo de vida del reproductor. Puede utilizarse dentro de una interfaz de usuario Compose
 * para integrar de manera fluida la reproducción de videos de YouTube.
 *
 * @param youtubeVideoId El ID del video de YouTube que se cargará y reproducirá.
 * @param lifecycleOwner El propietario del ciclo de vida que controla el ciclo de vida del componente Compose.
 *
 * Basado en este video como implementar
 * https://www.youtube.com/watch?v=E_8LHkn4g-Q
 */
@Composable
fun YoutubePlayer(
    youtubeVideoId: String,
    lifecycleOwner: LifecycleOwner
) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(SIZE8.dp)
            .clip(RoundedCornerShape(SIZE16.dp)),
        factory = { context ->

            val youTubePlayerView = YouTubePlayerView(context)

            youTubePlayerView.apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        youTubePlayerView.release()
                    }
                })
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youtubeVideoId, 0f)
                        youTubePlayer.play()
                        youTubePlayer.setVolume(100)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        if (state == PlayerConstants.PlayerState.ENDED) {
                            youTubePlayer.loadVideo(youtubeVideoId, 0f)
                            youTubePlayer.play()
                        }
                    }
                })
            }
        })
}