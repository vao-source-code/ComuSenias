package com.example.comusenias.util

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.example.comusenias.presentation.component.defaults.app.CircularProgressBar

@OptIn(UnstableApi::class)
@Composable
fun PlayVideo(videoUrl: String) {
    val context = LocalContext.current
    val videoPlayer = remember { VideoPlayer(context, videoUrl) }

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
    showVideo(isVideoReady, videoPlayer)
}

@Composable
private fun showVideo(
    isVideoReady: MutableState<Boolean>,
    videoPlayer: VideoPlayer
) {
    if (isVideoReady.value) {
        AndroidView(
            factory = { videoPlayer.getPlayerView() },
            modifier = Modifier
                .aspectRatio(15f / 10f)
                .fillMaxWidth()
        )
    } else {
        CircularProgressBar()
    }
}