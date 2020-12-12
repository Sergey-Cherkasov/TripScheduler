package br.svcdev.tripscheduler.model.repository.remote.iatacodes

import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.model.api.IAutoCompleteSource
import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject

class IataCodesRepository(private val api: IAutoCompleteSource) : IIataCodesRepository {

    val logger = Logger()

    var subject: PublishSubject<String> = PublishSubject.create()

    override fun getIataCodes(query: String): Single<List<IataCode>> =
        api.loadIataCodes(query)
            .flatMap { repository ->
                Single.fromCallable {
                    repository
                }
            }
            .subscribeOn(Schedulers.io())

}