/*
 * Created on 2020-3-8 12:30:20 AM.
 * Copyright © 2020 刘振林. All rights reserved.
 */

package com.liuzhenlin.texturevideoview;

import android.content.Context;
import android.view.Surface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Stub of VlcVideoPlayer
 *
 * @author 刘振林
 */
public class VlcVideoPlayer extends VideoPlayer {

    public VlcVideoPlayer(@NonNull Context context) {
        super(context);
    }

    @Override
    protected boolean isPlayerCreated() {
        return false;
    }

    @Override
    protected void onVideoSurfaceChanged(@Nullable Surface surface) {

    }

    @Override
    protected void openVideoInternal(@Nullable Surface surface) {

    }

    @Override
    protected void closeVideoInternal(boolean fromUser) {

    }

    @Override
    public void setVideoResourceId(int resId) {

    }

    @Override
    public void restartVideo() {

    }

    @Override
    public void play(boolean fromUser) {

    }

    @Override
    public void pause(boolean fromUser) {

    }

    @Override
    public void seekTo(int positionMs, boolean fromUser) {

    }

    @Override
    public int getVideoProgress() {
        return 0;
    }

    @Override
    public int getVideoBufferProgress() {
        return 0;
    }
}
