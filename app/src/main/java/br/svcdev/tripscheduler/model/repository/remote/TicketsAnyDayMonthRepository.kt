package br.svcdev.tripscheduler.model.repository.remote

import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.model.api.IDataSource
import br.svcdev.tripscheduler.model.entity.TicketAnyDayMonth
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TicketsAnyDayMonthRepository(val api: IDataSource) : ITicketsAnyDayMonthRepository {

    private val logger = Logger()

    override fun getTickets(origin: String?, destination: String?, departAt: String?):
            Single<TicketAnyDayMonth> =
        api.loadTicketsAnyDayMonth(
            origin,
            destination,
            departAt,
            null,
            "departure_date",
            "rub",
            "90ccf353a3189572c8627088f90d2cbe"
        ).flatMap { result ->
            Single.fromCallable {
                result
            }
        }.subscribeOn(Schedulers.io())
}