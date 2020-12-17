package br.svcdev.tripscheduler.di.searchticketsscreen

import br.svcdev.tripscheduler.di.listticketsscreen.IListTicketsSubComponent
import br.svcdev.tripscheduler.di.searchticketsscreen.module.IataCodesRepositoryModule
import br.svcdev.tripscheduler.presenter.SearchTicketsPresenter
import dagger.Subcomponent

@SearchTicketsScope
@Subcomponent(
    modules = [IataCodesRepositoryModule::class]
)
interface ISearchTicketsSubComponent {
    fun listTicketsSubComponent(): IListTicketsSubComponent
    fun inject(searchTicketsPresenter: SearchTicketsPresenter)
}