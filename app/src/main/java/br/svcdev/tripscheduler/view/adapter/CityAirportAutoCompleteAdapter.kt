package br.svcdev.tripscheduler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import br.svcdev.tripscheduler.R
import br.svcdev.tripscheduler.common.Logger
import br.svcdev.tripscheduler.model.entity.cityairport.IataCode
import br.svcdev.tripscheduler.presenter.interfaces.IAutoCompletePresenter
import br.svcdev.tripscheduler.view.fragment.SearchTicketsFragment
import java.util.*

class CityAirportAutoCompleteAdapter(
    private val context: SearchTicketsFragment,
    private val presenter: IAutoCompletePresenter
) : BaseAdapter(), Filterable {

    companion object {
        private const val MAX_RESULTS = 5
    }

    private var listIataCode: MutableList<IataCode> = mutableListOf()
    private var suggestions: MutableList<IataCode> = mutableListOf()

    private val logger = Logger()

    override fun getCount(): Int = listIataCode.size

    override fun getItem(position: Int) = listIataCode[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context.context)
            view = inflater.inflate(R.layout.item_autocomplete_dropdown, parent, false)
        }
        view?.let {
            val iataCode: IataCode = getItem(position)
            (it.findViewById(R.id.tv_city_airport_name) as TextView).text = iataCode.name
            (it.findViewById(R.id.tv_city_airport_iata) as TextView).text = iataCode.code
        }
        return view!!
    }

    private val filter = object : Filter() {
        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as IataCode).name
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            constraint?.let {
                suggestions.clear()
                suggestions.addAll(presenter.getIataCodes(it.toString().capitalize(Locale.ROOT)))
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                return filterResults
            } ?: return FilterResults()
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                if (it.count > 0) {
                    with(listIataCode) {
                        clear()
                        addAll(it.values as List<IataCode>)
                        notifyDataSetChanged()
                    }
                } else {
                    notifyDataSetInvalidated()
                }
            } ?: notifyDataSetInvalidated()
        }
    }

    override fun getFilter(): Filter = filter

}