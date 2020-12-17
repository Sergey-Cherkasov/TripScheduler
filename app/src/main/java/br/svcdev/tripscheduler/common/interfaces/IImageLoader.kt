package br.svcdev.tripscheduler.common.interfaces

interface IImageLoader<T> {
    fun loadLogo(airlineName: String?, container: T, lenght: Int, width: Int)
}