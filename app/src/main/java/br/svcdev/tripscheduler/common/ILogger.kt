package br.svcdev.tripscheduler.common

interface ILogger {
    fun logd(tag: String?, message: String)
    fun logd(tag: String?, message: String, throwable: Throwable)
    fun loge(tag: String?, message: String)
    fun loge(tag: String?, message: String, throwable: Throwable)
    fun logi(tag: String?, message: String)
    fun logi(tag: String?, message: String, throwable: Throwable)
    fun logv(tag: String?, message: String)
    fun logv(tag: String?, message: String, throwable: Throwable)
    fun logw(tag: String?, message: String)
    fun logw(tag: String?, message: String, throwable: Throwable)
}