package br.svcdev.tripscheduler.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.svcdev.tripscheduler.R
import br.svcdev.tripscheduler.common.interfaces.IImageLoader
import br.svcdev.tripscheduler.presenter.interfaces.ITicketsPresenter
import br.svcdev.tripscheduler.view.interfaces.IListTicketsItemView

class ListTicketsRVAdapter(
    val presenter: ITicketsPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<ListTicketsRVAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        IListTicketsItemView {

        private val LENGHT = 175
        private val WIDTH = 50
        private val currency = "RUB"

        private val ivAirlineLogo: ImageView = itemView.findViewById(R.id.iv_airline_logo)
        private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
        private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

        override fun setAirlineLogo(airline: String?) = imageLoader.loadLogo(
            airline, ivAirlineLogo,
            LENGHT, WIDTH
        )

        override fun setDate(date: String) {
            tvDate.text = String.format("Date: $date")
        }

        override fun setPrice(price: Int) {
            tvPrice.text = String.format("Price: $price $currency")
        }

        override fun getDate(): String = tvDate.text.toString()

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