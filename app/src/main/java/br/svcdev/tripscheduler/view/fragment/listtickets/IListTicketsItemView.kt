package br.svcdev.tripscheduler.view.fragment.listtickets

import br.svcdev.tripscheduler.view.IItemView

interface IListTicketsItemView : IItemView {
    fun setAirlineLogo(airline: String?)
    fun setAirlineName(airline: String)
    fun setDate(date: String)
    fun setFlightNumber(number: Int)
    fun setPrice(price: Int)
}