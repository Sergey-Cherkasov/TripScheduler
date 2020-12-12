package br.svcdev.tripscheduler.presenter

import br.svcdev.tripscheduler.common.ILogger
import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import br.svcdev.tripscheduler.model.repository.remote.iatacodes.IIataCodesRepository
import br.svcdev.tripscheduler.view.Screens
import br.svcdev.tripscheduler.view.fragment.searchtickets.ISearchTicketsView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SearchTicketsPresenter : MvpPresenter<ISearchTicketsView>(), ISearchTicketsPresenter {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var iataCodesRepository: IIataCodesRepository

    private val logger: ILogger = Logger()

    val autoCompletePresenter = AutoCompletePresenter()

    override var buttonClickListener: (String, String, String) -> Unit =
        { origin, destination, departAt ->
            router.navigateTo(Screens.ListTicketsScreen(origin, destination, departAt))
        }

    override var textChangeListener: (String) -> Unit = {
        logger.logi("TRIP_SCHEDULER", "Text changed: $it")
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    override fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    inner class AutoCompletePresenter : IAutoCompletePresenter {
        private val iataCodesList = mutableListOf<IataCode>()

        override fun getIataCodes(query: String): MutableList<IataCode> {

            iataCodesRepository.getIataCodes(query)
                .observeOn(mainThreadScheduler)
                .subscribe(
                    { iataCodes ->
                        iataCodesList.clear()
                        iataCodesList.addAll(iataCodes)
                    },
                    { error -> logger.loge("TRIP_SCHEDULER", error.message!!) })

            return iataCodesList
        }

    }

}