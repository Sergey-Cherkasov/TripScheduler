package br.svcdev.tripscheduler.di.ticketdetailsscreen

import br.svcdev.tripscheduler.presenter.TicketDetailsPresenter
import dagger.Subcomponent

@TicketDetailsScope
@Subcomponent
interface ITicketDetailsSubComponent {
    fun inject(ticketDetailsPresenter: TicketDetailsPresenter)
}