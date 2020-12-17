package br.svcdev.tripscheduler.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.svcdev.tripscheduler.App
import br.svcdev.tripscheduler.common.ImageLoader
import br.svcdev.tripscheduler.common.interfaces.IBackPressedListener
import br.svcdev.tripscheduler.databinding.FragmentTicketDetailsBinding
import br.svcdev.tripscheduler.model.entity.Data
import br.svcdev.tripscheduler.presenter.TicketDetailsPresenter
import br.svcdev.tripscheduler.view.interfaces.ITicketDetailsView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class TicketDetailsFragment : MvpAppCompatFragment(), ITicketDetailsView, IBackPressedListener {

    companion object {
        fun newInstance(ticket: Data) = TicketDetailsFragment().apply {
            arguments = Bundle().apply { putParcelable("ticket", ticket) }
        }

        private const val LENGTH = 175
        private const val WIDTH = 50
    }

    private val presenter by moxyPresenter {
        val ticket = arguments?.getParcelable<Data>("ticket")!!
        App.instance.initITicketDetailsSubComponent()
        TicketDetailsPresenter(ticket).apply {
            App.instance.ticketDetailsSubComponent?.inject(this)
        }
    }

    private var binding: FragmentTicketDetailsBinding? = null
    private val imageLoader = ImageLoader()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun release() {
        App.instance.releaseITicketDetailsSubComponent()
    }

    override fun setAirlineLogo(airline: String) {
        imageLoader.loadLogo(airline, binding!!.ivDetailsAirlineLogo, LENGTH, WIDTH)
    }

    override fun setDate(date: String) {
        binding?.tvDetailsDate?.text = String.format("Date: $date")
    }

    override fun setPrice(price: Int) {
        binding?.tvDetailsPrice?.text = String.format("Price: $price")
    }

    override fun setFlight(airline: String, flightNumber: Int) {
        binding?.tvDetailsFlight?.text = String.format("Flight: $airline $flightNumber")
    }

    override fun setTransfers(transfers: Int) {
        binding?.tvDetailsTransfers?.text = String.format("Transfers: $transfers")
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()
}