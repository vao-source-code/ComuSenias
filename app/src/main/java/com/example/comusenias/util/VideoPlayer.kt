 package com.example.comusenias.util

import android.content.Context
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.common.util.Util
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView

 @OptIn(UnstableApi::class)
 class VideoPlayer(private val context: Context, private val videoUrl: String) {
     private lateinit var exoPlayer: SimpleExoPlayer
     private val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
         context,
         Util.getUserAgent(context, context.packageName)
     )

     fun initializePlayer(): SimpleExoPlayer {
         if (!::exoPlayer.isInitialized) {
             exoPlayer = SimpleExoPlayer.Builder(context).build().apply {
                 playWhenReady = true
                 repeatMode = Player.REPEAT_MODE_ALL
             }
         }
         return exoPlayer
     }

     fun preparePlayer() {
         val source = createMediaSource()
         exoPlayer.prepare(source)
     }

     private fun createMediaSource(): MediaSource {
         return ProgressiveMediaSource.Factory(dataSourceFactory)
             .createMediaSource(MediaItem.fromUri(videoUrl))
     }

     fun releasePlayer() {
         exoPlayer.release()
     }

     fun getPlayerView(): PlayerView {
         return PlayerView(context).apply {
             player = exoPlayer
             useController = false
         }
     }
 }
