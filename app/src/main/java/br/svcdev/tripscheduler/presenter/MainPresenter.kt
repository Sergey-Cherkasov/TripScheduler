package br.svcdev.tripscheduler.presenter

import br.svcdev.tripscheduler.view.Screens
import br.svcdev.tripscheduler.view.interfaces.IMainView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter : MvpPresenter<IMainView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.SearchTicketsScreen())
    }

    fun onBackPressed() {
        router.exit()
    }
}