package br.svcdev.tripscheduler

import android.app.Application
import br.svcdev.tripscheduler.di.DaggerIAppComponent
import br.svcdev.tripscheduler.di.IAppComponent
import br.svcdev.tripscheduler.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerIAppComponent.builder().appModule(AppModule(instance)).build()
    }
}