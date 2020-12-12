package br.svcdev.tripscheduler.di

import br.svcdev.tripscheduler.di.module.ApiModule
import br.svcdev.tripscheduler.di.module.AppModule
import br.svcdev.tripscheduler.di.module.CiceroneModule
import br.svcdev.tripscheduler.di.module.RepositoryModule
import br.svcdev.tripscheduler.presenter.ListTicketsPresenter
import br.svcdev.tripscheduler.presenter.MainPresenter
import br.svcdev.tripscheduler.presenter.SearchTicketsPresenter
import br.svcdev.tripscheduler.view.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        RepositoryModule::class
    ]
)
interface IAppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
    fun inject(searchTicketsPresenter: SearchTicketsPresenter)
    fun inject(listTicketsPresenter: ListTicketsPresenter)
}