package br.svcdev.tripscheduler.model.repository.remote

import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.common.interfaces.INetworkStatus
import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import br.svcdev.tripscheduler.model.interfaces.IIataCodesRepository
import br.svcdev.tripscheduler.model.interfaces.api.IAutoCompleteSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class IataCodesRepository(
    private val api: IAutoCompleteSource,
    private val networkStatus: INetworkStatus
) : IIataCodesRepository {

    val logger = Logger()

    override fun getIataCodes(query: String): Single<List<IataCode>> =
        networkStatus.isOnlineSingle().subscribeOn(Schedulers.io()).flatMap { isOnline ->
            if (isOnline) {
                api.loadIataCodes(query).flatMap { list ->
                    Single.fromCallable {
                        list
                    }
                }.subscribeOn(Schedulers.io())
            } else {
                Single.fromCallable {
                    mutableListOf<IataCode>()
                }
            }
        }
}