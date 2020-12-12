package br.svcdev.tripscheduler.presenter

import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.model.entity.Data
import br.svcdev.tripscheduler.model.repository.remote.ITicketsAnyDayMonthRepository
import br.svcdev.tripscheduler.view.fragment.listtickets.IListTicketsItemView
import br.svcdev.tripscheduler.view.fragment.listtickets.IListTicketsView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ListTicketsPresenter(
    private val origin: String?,
    private val destination: String?,
    private val departAt: String?
) : MvpPresenter<IListTicketsView>(), IListTicketsPresenter {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var ticketsAnyDayMonthRepository: ITicketsAnyDayMonthRepository

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    private val logger = Logger()

    val ticketsPresenter = TicketsPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()
        ticketsPresenter.itemLongClickListener = null
    }

    private fun loadData() {
        ticketsAnyDayMonthRepository.getTickets(origin, destination, departAt)
            .observeOn(mainThreadScheduler)
            .subscribe(
                { repos ->
                    ticketsPresenter.listTickets.clear()
                    ticketsPresenter.listTickets.putAll(repos.mapData)
                    viewState.updateList()
                },
                {
                    println("Error: ${it.message}")
                }
            )
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    inner class TicketsPresenter : ITicketsPresenter {
        val listTickets = mutableMapOf<String, Data>()

        override var itemClickListener: ((IListTicketsItemView) -> Unit)? = null

        override var itemLongClickListener: ((IListTicketsItemView) -> Unit)? = null

        override fun bindView(view: IListTicketsItemView) {
            val mapKeys = listTickets.keys
            val ticketAnyDayMonth = listTickets[mapKeys.elementAt(view.pos)]
            mapKeys.elementAt(view.pos).let { view.setDate(it) }
            ticketAnyDayMonth?.price.let { view.setPrice(it!!) }
            ticketAnyDayMonth?.airline.let { airlineName ->
                view.setAirlineName(airlineName!!)
                view.setAirlineLogo(airlineName)
            }
            ticketAnyDayMonth?.flightNumber.let { view.setFlightNumber(it!!) }
        }

        override fun getCount(): Int = listTickets.size
    }
}