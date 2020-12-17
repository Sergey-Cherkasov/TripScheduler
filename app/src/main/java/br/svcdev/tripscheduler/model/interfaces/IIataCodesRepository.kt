package br.svcdev.tripscheduler.model.interfaces

import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import io.reactivex.rxjava3.core.Single

interface IIataCodesRepository {
    fun getIataCodes(query: String): Single<List<IataCode>>
}