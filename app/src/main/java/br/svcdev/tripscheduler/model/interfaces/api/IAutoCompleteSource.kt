package br.svcdev.tripscheduler.model.interfaces.api

import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IAutoCompleteSource {
    @GET("/places2")
    fun loadIataCodes(
        @Query("term") term: String,
        @Query("locale") locale: String = "en",
        @Query("types[]") types: Array<String> = arrayOf("city", "airport")
    ): Single<List<IataCode>>

    @GET("/places2")
    fun loadCodes(
        @Query("term") term: String,
        @Query("locale") locale: String = "en",
        @Query("types[]") types: Array<String> = arrayOf("city", "airport")
    ): Observable<List<IataCode>>

}