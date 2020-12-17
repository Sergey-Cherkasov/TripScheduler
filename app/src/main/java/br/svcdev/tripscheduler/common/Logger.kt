package br.svcdev.tripscheduler.common

import android.util.Log
import br.svcdev.tripscheduler.common.interfaces.ILogger

class Logger : ILogger {

    companion object {
        const val TAG = "TRIP_SCHEDULER"
    }

    override fun logd(tag: String?, message: String) {
        Log.d(tag, message)
    }

    override fun logd(tag: String?, message: String, throwable: Throwable) {
        Log.d(tag, message, throwable)
    }

    override fun loge(tag: String?, message: String) {
        Log.e(tag, message)
    }

    override fun loge(tag: String?, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }

    override fun logi(tag: String?, message: String) {
        Log.i(tag, message)
    }

    override fun logi(tag: String?, message: String, throwable: Throwable) {
        Log.i(tag, message, throwable)
    }

    override fun logv(tag: String?, message: String) {
        Log.v(tag, message)
    }

    override fun logv(tag: String?, message: String, throwable: Throwable) {
        Log.v(tag, message, throwable)
    }

    override fun logw(tag: String?, message: String) {
        Log.w(tag, message)
    }

    override fun logw(tag: String?, message: String, throwable: Throwable) {
        Log.w(tag, message, throwable)
    }
}