package br.svcdev.tripscheduler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.tripscheduler.R
import br.svcdev.tripscheduler.common.IImageLoader
import br.svcdev.tripscheduler.presenter.ITicketsPresenter
import br.svcdev.tripscheduler.view.fragment.listtickets.IListTicketsItemView

class ListTicketsRVAdapter(
    val presenter: ITicketsPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<ListTicketsRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        IListTicketsItemView {

        private val ivAirlineLogo: ImageView = itemView.findViewById(R.id.iv_airline_logo)
        private val tvAirline: TextView = itemView.findViewById(R.id.tv_airline)
        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private val tvFlightNumber: TextView = itemView.findViewById(R.id.tv_flight_number)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

        override fun setAirlineLogo(airline: String?) = imageLoader.loadLogo(airline, ivAirlineLogo)

        override fun setAirlineName(airline: String) {
            tvAirline.text = airline
        }

        override fun setDate(date: String) {
            tvDate.text = date
        }

        override fun setFlightNumber(number: Int) {
            tvFlightNumber.text = number.toString()
        }

        override fun setPrice(price: Int) {
            tvPrice.text = price.toString()
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_tickets, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.itemView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        holder.itemView.setOnLongClickListener {
            presenter.itemLongClickListener?.invoke(holder)
            true
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int = presenter.getCount()
}