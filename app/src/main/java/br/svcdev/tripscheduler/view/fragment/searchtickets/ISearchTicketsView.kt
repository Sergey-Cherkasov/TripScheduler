package br.svcdev.tripscheduler.view.fragment.searchtickets

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ISearchTicketsView : MvpView {
    fun init()
}