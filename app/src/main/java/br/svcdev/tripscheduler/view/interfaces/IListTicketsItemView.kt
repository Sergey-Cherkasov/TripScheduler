package br.svcdev.tripscheduler.view.interfaces

interface IListTicketsItemView : IItemView {
    fun setAirlineLogo(airline: String?)
    fun setDate(date: String)
    fun setPrice(price: Int)
    fun getDate(): String
}