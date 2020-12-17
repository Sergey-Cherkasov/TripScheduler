package br.svcdev.tripscheduler.view

import androidx.fragment.app.Fragment
import br.svcdev.tripscheduler.model.entity.Data
import br.svcdev.tripscheduler.view.fragment.ListTicketsFragment
import br.svcdev.tripscheduler.view.fragment.SearchTicketsFragment
import br.svcdev.tripscheduler.view.fragment.TicketDetailsFragment
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

    class TicketDetailsScreen(val ticket: Data) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return TicketDetailsFragment.newInstance(ticket)
        }
    }
}