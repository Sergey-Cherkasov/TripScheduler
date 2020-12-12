package br.svcdev.tripscheduler.view

import androidx.fragment.app.Fragment
import br.svcdev.tripscheduler.view.fragment.listtickets.ListTicketsFragment
import br.svcdev.tripscheduler.view.fragment.searchtickets.SearchTicketsFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class SearchTicketsScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchTicketsFragment.newInstance()
        }
    }

    class ListTicketsScreen(val origin: String, val destination: String, val departAt: String) :
        SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ListTicketsFragment.newInstance(origin, destination, departAt)
        }
    }
}