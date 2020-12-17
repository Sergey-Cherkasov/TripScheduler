package br.svcdev.tripscheduler.presenter

import br.svcdev.tripscheduler.model.entity.Data
import br.svcdev.tripscheduler.presenter.interfaces.ITicketDetailsPresenter
import br.svcdev.tripscheduler.view.interfaces.ITicketDetailsView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class TicketDetailsPresenter(val ticket: Data) : MvpPresenter<ITicketDetailsView>(),
    ITicketDetailsPresenter {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        ticket.apply {
            viewState.setAirlineLogo(airline)
            viewState.setDate(departureAt)
            viewState.setPrice(price)
            viewState.setFlight(airline, flightNumber)
            viewState.setTransfers(transfers)
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}