package br.svcdev.tripscheduler.di.searchticketsscreen.module

import br.svcdev.tripscheduler.common.interfaces.INetworkStatus
import br.svcdev.tripscheduler.di.searchticketsscreen.SearchTicketsScope
import br.svcdev.tripscheduler.model.interfaces.IIataCodesRepository
import br.svcdev.tripscheduler.model.interfaces.api.IAutoCompleteSource
import br.svcdev.tripscheduler.model.repository.remote.IataCodesRepository
import dagger.Module
import dagger.Provides

@Module
class IataCodesRepositoryModule {
    @SearchTicketsScope
    @Provides
    fun iataCodesRepository(autocompleteApi: IAutoCompleteSource, networkStatus: INetworkStatus):
            IIataCodesRepository = IataCodesRepository(autocompleteApi, networkStatus)
}