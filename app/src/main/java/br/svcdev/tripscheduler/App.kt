package br.svcdev.tripscheduler

import android.app.Application
import br.svcdev.tripscheduler.di.DaggerIAppComponent
import br.svcdev.tripscheduler.di.IAppComponent
import br.svcdev.tripscheduler.di.listticketsscreen.IListTicketsSubComponent
import br.svcdev.tripscheduler.di.module.AppModule
import br.svcdev.tripscheduler.di.searchticketsscreen.ISearchTicketsSubComponent
import br.svcdev.tripscheduler.di.ticketdetailsscreen.ITicketDetailsSubComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: IAppComponent

    var searchTicketsSubComponent: ISearchTicketsSubComponent? = null
        private set
    var listTicketsSubComponent: IListTicketsSubComponent? = null
        private set
    var ticketDetailsSubComponent: ITicketDetailsSubComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerIAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun initISearchTicketsSubComponent() = appComponent.searchTicketsSubComponent().also {
        searchTicketsSubComponent = it
    }

    fun releaseISearchTicketsSubComponent() {
        searchTicketsSubComponent = null
    }

    fun initIListTicketsSubComponent() = searchTicketsSubComponent?.listTicketsSubComponent().also {
        listTicketsSubComponent = it
    }

    fun releaseIListTicketsSubComponent() {
        listTicketsSubComponent = null
    }

    fun initITicketDetailsSubComponent() = listTicketsSubComponent?.ticketDetailsSubComponent()
        .also { ticketDetailsSubComponent = it }

    fun releaseITicketDetailsSubComponent() {
        ticketDetailsSubComponent = null
    }

}