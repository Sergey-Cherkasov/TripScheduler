package br.svcdev.tripscheduler.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.svcdev.tripscheduler.App
import br.svcdev.tripscheduler.R
import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.databinding.FragmentSearchTicketsBinding
import br.svcdev.tripscheduler.common.interfaces.IBackPressedListener
import br.svcdev.tripscheduler.view.interfaces.ISearchTicketsView
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
        App.instance.initISearchTicketsSubComponent()
        SearchTicketsPresenter().apply {
            App.instance.searchTicketsSubComponent?.inject(this)
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

        binding.btnFindTickets.isEnabled = onButtonEnabled()

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

        binding.fromToDestination.etFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnFindTickets.isEnabled = onButtonEnabled()
            }

        })

        binding.fromToDestination.etTo.setOnItemClickListener { parent, _, position, _ ->
            val iataCode: IataCode = parent.getItemAtPosition(position) as IataCode
            binding.fromToDestination.tvIataTo.text = iataCode.code
        }

        binding.fromToDestination.etTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnFindTickets.isEnabled = onButtonEnabled()
            }

        })

        binding.fromToDates.etDepartAt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val stringBuilder = StringBuilder()

                if (s.toString().matches("20[2-3][0-9]".toRegex())) {
                    stringBuilder.append(s)
                    stringBuilder.append("-")
                    binding.fromToDates.etDepartAt.setText("")
                    binding.fromToDates.etDepartAt.append(stringBuilder)
                }

                if (!s.toString().matches("(20[2-3][0-9])-(0[1-9]|1[0-2])".toRegex())) {
                    binding.fromToDates.etDepartAt.setTextColor(
                        resources.getColor(
                            R.color.red,
                            null
                        )
                    )
                    binding.fromToDates.tilDepartAt.error = "Wrong value (e.g. yyyy-mm)"
                } else {
                    binding.fromToDates.etDepartAt.setTextColor(
                        resources.getColor(
                            R.color.black,
                            null
                        )
                    )
                    binding.fromToDates.tilDepartAt.error = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {
                binding.btnFindTickets.isEnabled = onButtonEnabled()
            }

        })

        binding.fromToDates.etReturnAt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val stringBuilder = StringBuilder()

                if (s.toString().matches("20\\d\\d".toRegex())) {
                    stringBuilder.append(s)
                    stringBuilder.append("-")
                    binding.fromToDates.etReturnAt.setText("")
                    binding.fromToDates.etReturnAt.append(stringBuilder)
                }

                if (s.toString().matches("(20\\d\\d)-(0[1-9]|1[0-2])\\d+".toRegex())) {
                    binding.fromToDates.etReturnAt.setTextColor(
                        resources.getColor(
                            R.color.red,
                            null
                        )
                    )
                    binding.fromToDates.tilReturnAt.error = "Wrong value (e.g. yyyy-mm)"
                } else {
                    binding.fromToDates.etReturnAt.setTextColor(
                        resources.getColor(
                            R.color.black,
                            null
                        )
                    )
                    binding.fromToDates.tilReturnAt.error = ""
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.fromToDates.ivReturnAt.visibility = View.INVISIBLE
        binding.fromToDates.tilReturnAt.visibility = View.INVISIBLE
        binding.fromToDates.tvReturnAt.visibility = View.VISIBLE
        binding.fromToDates.tvReturnAt.setOnClickListener { _ ->
            run {
                binding.fromToDates.ivReturnAt.visibility = View.VISIBLE
                binding.fromToDates.tilReturnAt.visibility = View.VISIBLE
                binding.fromToDates.ivReturnAtClose.visibility = View.VISIBLE
                binding.fromToDates.tvReturnAt.visibility = View.INVISIBLE
            }
        }
        binding.fromToDates.ivReturnAtClose.visibility = View.INVISIBLE
        binding.fromToDates.ivReturnAtClose.setOnClickListener { _ ->
            run {
                binding.fromToDates.ivReturnAt.visibility = View.INVISIBLE
                binding.fromToDates.tilReturnAt.visibility = View.INVISIBLE
                binding.fromToDates.ivReturnAtClose.visibility = View.INVISIBLE
                binding.fromToDates.tvReturnAt.visibility = View.VISIBLE
            }
        }
    }

    override fun release() {
        App.instance.releaseISearchTicketsSubComponent()
    }

    private fun onButtonEnabled(): Boolean =
        !(binding.fromToDestination.tvIataFrom.text == "" ||
                binding.fromToDestination.tvIataTo.text == "" ||
                binding.fromToDates.tilDepartAt.editText?.text.toString() == "")

}