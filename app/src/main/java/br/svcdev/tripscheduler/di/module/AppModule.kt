package br.svcdev.tripscheduler.di.module

import br.svcdev.tripscheduler.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(private val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

}