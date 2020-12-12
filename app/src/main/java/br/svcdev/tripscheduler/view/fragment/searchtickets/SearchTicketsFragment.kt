package br.svcdev.tripscheduler.view.fragment.searchtickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.svcdev.tripscheduler.App
import br.svcdev.tripscheduler.common.IBackPressedListener
import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.databinding.FragmentSearchTicketsBinding
import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import br.svcdev.tripscheduler.presenter.SearchTicketsPresenter
import br.svcdev.tripscheduler.view.adapter.CityAirportAutoCompleteAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SearchTicketsFragment : MvpAppCompatFragment(), ISearchTicketsView, IBackPressedListener {

    companion object {
        fun newInstance() = SearchTicketsFragment()
    }

    private val logger = Logger()

    private lateinit var binding: FragmentSearchTicketsBinding

    private val presenter by moxyPresenter {
        SearchTicketsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun init() {
        binding.btnFindTickets.setOnClickListener {
            presenter.buttonClickListener.invoke(
                binding.fromToDestination.tvIataFrom.text.toString(),
                binding.fromToDestination.tvIataTo.text.toString(),
                binding.fromToDates.etDepartAt.text.toString()
            )
        }

        binding.fromToDestination.etFrom.setAdapter(
            CityAirportAutoCompleteAdapter(this, presenter.autoCompletePresenter)
        )

        binding.fromToDestination.etFrom.setOnItemClickListener { parent, _, position, _ ->
            val iataCode: IataCode = parent.getItemAtPosition(position) as IataCode
            binding.fromToDestination.tvIataFrom.text = iataCode.code
        }

        binding.fromToDestination.etTo.setAdapter(
            CityAirportAutoCompleteAdapter(this, presenter.autoCompletePresenter)
        )

        binding.fromToDestination.etTo.setOnItemClickListener { parent, _, position, _ ->
            val iataCode: IataCode = parent.getItemAtPosition(position) as IataCode
            binding.fromToDestination.tvIataTo.text = iataCode.code
        }

    }

}