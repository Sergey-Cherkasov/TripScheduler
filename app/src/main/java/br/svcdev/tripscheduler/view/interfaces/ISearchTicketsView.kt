package br.svcdev.tripscheduler.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ISearchTicketsView : MvpView {
    fun init()
    fun release()
}