package br.svcdev.tripscheduler.model.repository.remote

import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.common.interfaces.INetworkStatus
import br.svcdev.tripscheduler.model.entity.Data
import br.svcdev.tripscheduler.model.entity.TicketAnyDayMonth
import br.svcdev.tripscheduler.model.interfaces.ITicketsAnyDayMonthRepository
import br.svcdev.tripscheduler.model.interfaces.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TicketsAnyDayMonthRepository(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus
) : ITicketsAnyDayMonthRepository {

    private val logger = Logger()

    /*
    * TODO: Реализовать Single.fromCallable в ветке else в условном выражении. Сейчас там заглушка
    */
    override fun getTickets(
        origin: String?,
        destination: String?,
        departAt: String?
    ): Single<TicketAnyDayMonth> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
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
            }
        } else {
            Single.fromCallable {
                TicketAnyDayMonth(false, "", "", mutableMapOf<String, Data>())
            }
        }
    }.subscribeOn(Schedulers.io())
}