package br.svcdev.tripscheduler.di.listticketsscreen

import br.svcdev.tripscheduler.di.listticketsscreen.module.ListTicketsRepositoryModule
import br.svcdev.tripscheduler.di.ticketdetailsscreen.ITicketDetailsSubComponent
import br.svcdev.tripscheduler.presenter.ListTicketsPresenter
import dagger.Subcomponent

@ListTicketsScope
@Subcomponent(
    modules = [ListTicketsRepositoryModule::class]
)
interface IListTicketsSubComponent {
    fun ticketDetailsSubComponent(): ITicketDetailsSubComponent
    fun inject(listTicketsPresenter: ListTicketsPresenter)
}