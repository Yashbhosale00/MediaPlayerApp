package com.example.mediaplayerapp;

import android.content.Context;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;

public class MediaPlayerHelper {
    private ExoPlayer player;

    public MediaPlayerHelper(Context context) {
        player = new ExoPlayer.Builder(context).build();
    }

    public void setMediaSource(String url) {
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare();
    }

    public void play() {
        player.play();
    }

    public void pause() {
        player.pause();
    }

    public long getCurrentPosition() {
        return player.getCurrentPosition();
    }

    public void release() {
        player.release();
    }
}
