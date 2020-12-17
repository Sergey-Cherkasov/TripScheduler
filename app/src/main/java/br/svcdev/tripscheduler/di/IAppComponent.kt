package br.svcdev.tripscheduler.di

import br.svcdev.tripscheduler.di.module.ApiModule
import br.svcdev.tripscheduler.di.module.AppModule
import br.svcdev.tripscheduler.di.module.CiceroneModule
import br.svcdev.tripscheduler.di.searchticketsscreen.ISearchTicketsSubComponent
import br.svcdev.tripscheduler.presenter.MainPresenter
import br.svcdev.tripscheduler.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class
    ]
)
interface IAppComponent {
    fun searchTicketsSubComponent(): ISearchTicketsSubComponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}