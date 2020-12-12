package br.svcdev.tripscheduler.model.api

import br.svcdev.tripscheduler.model.entity.TicketAnyDayMonth
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IDataSource {
    @GET("/v1/prices/calendar")
    fun loadTicketsAnyDayMonth(
        @Query("origin") origin: String?,
        @Query("destination") destination: String?,
        @Query("depart_date") departDate: String?,
        @Query("return_date") returnDate: String? = null,
        @Query("calendar_type") calendarType: String? = "departure_date",
        @Query("currency") currency: String? = "rub",
        @Query("token") token: String? = "90ccf353a3189572c8627088f90d2cbe",
    ): Single<TicketAnyDayMonth>

}