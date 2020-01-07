/*
 * Created on 2018/05/14.
 * Copyright © 2018–2020 刘振林. All rights reserved.
 */

package com.liuzhenlin.videos;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.bumptech.glide.Glide;
import com.liuzhenlin.floatingmenu.DensityUtils;
import com.liuzhenlin.texturevideoview.utils.SystemBarUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;

/**
 * @author 刘振林
 */
public class App extends Application {

    private static App sApp;

    private static volatile String sAppDirectory;

    private volatile String mAuthority;

    private int mStatusHeight;

    private int mScreenWidth = -1;
    private int mScreenHeight = -1;

    private int mRealScreenWidth = -1;
    private int mRealScreenHeight = -1;

    private int mVideoThumbWidth = -1;

    private RefWatcher mRefWatcher;

//    static {
//        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        mRefWatcher = LeakCanary.install(this);

        sApp = this;
        mStatusHeight = SystemBarUtils.getStatusHeight(this);
        registerComponentCallbacks(Glide.get(this));
    }

    @NonNull
    public static App getInstance(@NonNull Context context) {
        return sApp == null ? (App) context.getApplicationContext() : sApp;
    }

    @Nullable
    public static App getInstanceUnsafe() {
        return sApp;
    }

    @NonNull
    public static String getAppDirectory() {
        if (sAppDirectory == null) {
            synchronized (App.class) {
                if (sAppDirectory == null) {
                    sAppDirectory = Environment.getExternalStorageDirectory() + File.separator
                            + "videos_lzl";
                }
            }
        }
        return sAppDirectory;
    }

    @NonNull
    public String getAuthority() {
        if (mAuthority == null) {
            synchronized (this) {
                if (mAuthority == null) {
                    mAuthority = getPackageName() + ".provider";
                }
            }
        }
        return mAuthority;
    }

    public int getStatusHeightInPortrait() {
        return mStatusHeight;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public int getScreenWidthIgnoreOrientation() {
        if (mScreenWidth == -1) {
            synchronized (this) {
                if (mScreenWidth == -1) {
                    int screenWidth = DensityUtils.getScreenWidth(this);
                    if (getResources().getConfiguration().orientation
                            != Configuration.ORIENTATION_PORTRAIT) {
                        //@formatter:off
                        int screenHeight  = DensityUtils.getScreenHeight(this);
                        if (screenWidth   > screenHeight) {
                            screenWidth  ^= screenHeight;
                            screenHeight ^= screenWidth;
                            screenWidth  ^= screenHeight;
                        }
                        //@formatter:on
                        mScreenHeight = screenHeight;
                    }
                    mScreenWidth = screenWidth;
                }
            }
        }
        return mScreenWidth;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public int getScreenHeightIgnoreOrientation() {
        if (mScreenHeight == -1) {
            synchronized (this) {
                if (mScreenHeight == -1) {
                    int screenHeight = DensityUtils.getScreenHeight(this);
                    if (getResources().getConfiguration().orientation
                            != Configuration.ORIENTATION_PORTRAIT) {
                        //@formatter:off
                        int screenWidth   = DensityUtils.getScreenWidth(this);
                        if (screenWidth   > screenHeight) {
                            screenWidth  ^= screenHeight;
                            screenHeight ^= screenWidth;
                        }
                        //@formatter:on
                        mScreenWidth = screenWidth;
                    }
                    mScreenHeight = screenHeight;
                }
            }
        }
        return mScreenHeight;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public int getRealScreenWidthIgnoreOrientation() {
        if (mRealScreenWidth == -1) {
            synchronized (this) {
                if (mRealScreenWidth == -1) {
                    int screenWidth = DensityUtils.getRealScreenWidth(this);
                    if (getResources().getConfiguration().orientation
                            != Configuration.ORIENTATION_PORTRAIT) {
                        //@formatter:off
                        int screenHeight  = DensityUtils.getRealScreenHeight(this);
                        if (screenWidth   > screenHeight) {
                            screenWidth  ^= screenHeight;
                            screenHeight ^= screenWidth;
                            screenWidth  ^= screenHeight;
                        }
                        //@formatter:on
                        mRealScreenHeight = screenHeight;
                    }
                    mRealScreenWidth = screenWidth;
                }
            }
        }
        return mRealScreenWidth;
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public int getRealScreenHeightIgnoreOrientation() {
        if (mRealScreenHeight == -1) {
            synchronized (this) {
                if (mRealScreenHeight == -1) {
                    int screenHeight = DensityUtils.getRealScreenHeight(this);
                    if (getResources().getConfiguration().orientation
                            != Configuration.ORIENTATION_PORTRAIT) {
                        //@formatter:off
                        int screenWidth   = DensityUtils.getRealScreenWidth(this);
                        if (screenWidth   > screenHeight) {
                            screenWidth  ^= screenHeight;
                            screenHeight ^= screenWidth;
                        }
                        //@formatter:on
                        mRealScreenWidth = screenWidth;
                    }
                    mRealScreenHeight = screenHeight;
                }
            }
        }
        return mRealScreenHeight;
    }

    public int getVideoThumbWidth() {
        if (mVideoThumbWidth == -1) {
            synchronized (this) {
                if (mVideoThumbWidth == -1) {
                    mVideoThumbWidth = (int) (getScreenWidthIgnoreOrientation() * 0.2778f + 0.5f);
                }
            }
        }
        return mVideoThumbWidth;
    }

    @NonNull
    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }
}
