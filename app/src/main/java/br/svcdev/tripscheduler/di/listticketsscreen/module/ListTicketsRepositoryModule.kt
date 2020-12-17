package br.svcdev.tripscheduler.di.listticketsscreen.module

import br.svcdev.tripscheduler.common.interfaces.INetworkStatus
import br.svcdev.tripscheduler.di.listticketsscreen.ListTicketsScope
import br.svcdev.tripscheduler.model.interfaces.ITicketsAnyDayMonthRepository
import br.svcdev.tripscheduler.model.interfaces.api.IDataSource
import br.svcdev.tripscheduler.model.repository.remote.TicketsAnyDayMonthRepository
import dagger.Module
import dagger.Provides

@Module
class ListTicketsRepositoryModule {
    @ListTicketsScope
    @Provides
    fun ticketsAnyDayMonthRepository(api: IDataSource, networkStatus: INetworkStatus):
            ITicketsAnyDayMonthRepository = TicketsAnyDayMonthRepository(api, networkStatus)
}