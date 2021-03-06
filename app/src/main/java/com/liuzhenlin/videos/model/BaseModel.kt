/*
 * Created on 2020-6-19 12:16:48 AM.
 * Copyright © 2020 刘振林. All rights reserved.
 */

package com.liuzhenlin.videos.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import java.util.*

/**
 * @author 刘振林
 */
abstract class BaseModel<Result>(context: Context) {

    protected val mContext: Context = context.applicationContext
    private var mLoader: AsyncTask<*, *, *>? = null
    private var mOnLoadListeners: MutableList<OnLoadListener<Result>>? = null

    fun addOnLoadListener(listener: OnLoadListener<Result>) {
        if (mOnLoadListeners == null)
            mOnLoadListeners = LinkedList()
        if (!mOnLoadListeners!!.contains(listener))
            mOnLoadListeners!!.add(listener)
    }

    fun removeOnLoadListener(listener: OnLoadListener<Result>) =
            mOnLoadListeners?.remove(listener)

    protected fun onLoadStart() {
        mOnLoadListeners?.let {
            for (listener in it.toTypedArray()) {
                listener.onLoadStart()
            }
        }
    }

    protected fun onLoadFinish(result: Result) {
        mLoader = null
        mOnLoadListeners?.let {
            for (listener in it.toTypedArray()) {
                listener.onLoadFinish(result)
            }
        }
    }

    private fun onLoadCanceled() {
        mLoader = null
        mOnLoadListeners?.let {
            for (listener in it.toTypedArray()) {
                listener.onLoadCanceled()
            }
        }
    }

    protected fun onLoadError(cause: Throwable) {
        mLoader!!.cancel(true)
        mLoader = null
        mOnLoadListeners?.let {
            for (listener in it.toTypedArray()) {
                listener.onLoadError(cause)
            }
        }
    }

    fun startLoader() {
        if (mLoader == null) {
            mLoader = createAndStartLoader()
        }
    }

    fun stopLoader() {
        val loader = mLoader
        if (loader != null) {
            loader.cancel(true)
            onLoadCanceled()
        }
    }

    protected abstract fun createAndStartLoader(): AsyncTask<*, *, *>

    @SuppressLint("StaticFieldLeak")
    protected abstract inner class Loader<Params, Progress> : AsyncTask<Params, Progress, Result>() {

        override fun onPreExecute() = onLoadStart()

        override fun onPostExecute(result: Result) = onLoadFinish(result)
    }
}