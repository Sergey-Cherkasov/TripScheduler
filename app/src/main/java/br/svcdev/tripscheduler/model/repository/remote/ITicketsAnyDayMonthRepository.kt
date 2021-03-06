package br.svcdev.tripscheduler.model.repository.remote

import br.svcdev.tripscheduler.model.entity.TicketAnyDayMonth
import io.reactivex.rxjava3.core.Single

interface ITicketsAnyDayMonthRepository {
    fun getTickets(origin: String?, destination: String?, departAt: String?):
            Single<TicketAnyDayMonth>
}