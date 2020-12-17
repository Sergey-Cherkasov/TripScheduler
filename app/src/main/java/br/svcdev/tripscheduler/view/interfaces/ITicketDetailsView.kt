package br.svcdev.tripscheduler.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ITicketDetailsView : MvpView {
    fun release()
    fun setAirlineLogo(airline: String)
    fun setDate(date: String)
    fun setPrice(price: Int)
    fun setFlight(airline: String, flightNumber: Int)
    fun setTransfers(transfers: Int)
}