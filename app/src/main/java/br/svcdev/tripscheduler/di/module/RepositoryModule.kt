package br.svcdev.tripscheduler.di.module

import br.svcdev.tripscheduler.model.api.IAutoCompleteSource
import br.svcdev.tripscheduler.model.api.IDataSource
import br.svcdev.tripscheduler.model.repository.remote.ITicketsAnyDayMonthRepository
import br.svcdev.tripscheduler.model.repository.remote.TicketsAnyDayMonthRepository
import br.svcdev.tripscheduler.model.repository.remote.iatacodes.IIataCodesRepository
import br.svcdev.tripscheduler.model.repository.remote.iatacodes.IataCodesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun iataCodesRepository(autocompleteApi: IAutoCompleteSource): IIataCodesRepository =
        IataCodesRepository(autocompleteApi)

    @Singleton
    @Provides
    fun ticketsAnyDayMonthRepository(api: IDataSource): ITicketsAnyDayMonthRepository =
        TicketsAnyDayMonthRepository(api)
}