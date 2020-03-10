/*
 * Created on 2020-3-9 3:45:03 AM.
 * Copyright © 2020 刘振林. All rights reserved.
 */

package com.google.android.exoplayer2.ext.ffmpeg

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.google.android.exoplayer2.ext.ffmpeg.test", appContext.packageName)
    }
}
