package br.svcdev.tripscheduler.common

interface IImageLoader<T> {
    fun loadLogo(airlineName: String?, container: T)
}