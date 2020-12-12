package br.svcdev.tripscheduler.view.fragment.listtickets

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface IListTicketsView : MvpView {
    fun init()
    fun updateList()
}