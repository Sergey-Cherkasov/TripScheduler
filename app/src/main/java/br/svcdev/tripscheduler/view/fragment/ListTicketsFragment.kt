package br.svcdev.tripscheduler.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import br.svcdev.tripscheduler.App
import br.svcdev.tripscheduler.common.interfaces.IBackPressedListener
import br.svcdev.tripscheduler.common.ImageLoader
import br.svcdev.tripscheduler.databinding.FragmentListTicketsBinding
import br.svcdev.tripscheduler.presenter.ListTicketsPresenter
import br.svcdev.tripscheduler.view.adapter.ListTicketsRVAdapter
import br.svcdev.tripscheduler.view.interfaces.IListTicketsView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ListTicketsFragment : MvpAppCompatFragment(), IListTicketsView, IBackPressedListener {

    companion object {
        fun newInstance(origin: String, destination: String, departAt: String) =
            ListTicketsFragment().apply {
                arguments = Bundle().apply {
                    putString("origin", origin)
                    putString("destination", destination)
                    putString("departAt", departAt)
                }
            }
    }

    private val presenter by moxyPresenter {
        val origin = arguments?.getString("origin")
        val destination = arguments?.getString("destination")
        val departAt = arguments?.getString("departAt")
        App.instance.initIListTicketsSubComponent()
        ListTicketsPresenter(origin, destination, departAt)
            .apply { App.instance.listTicketsSubComponent?.inject(this) }
    }

    private lateinit var binding: FragmentListTicketsBinding
    private var adapter: ListTicketsRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onBackPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun init() {
        binding.rvListTickets.layoutManager = LinearLayoutManager(context)
        adapter = ListTicketsRVAdapter(presenter.ticketsPresenter, ImageLoader())
        binding.rvListTickets.adapter = adapter
    }

    override fun release() {
        App.instance.releaseIListTicketsSubComponent()
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}