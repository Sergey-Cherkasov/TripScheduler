package br.svcdev.tripscheduler.presenter

import br.svcdev.tripscheduler.view.IItemView

interface IListPresenter<I : IItemView> {
    var itemClickListener: ((I) -> Unit)?
    var itemLongClickListener: ((I) -> Unit)?
    fun bindView(view: I)
    fun getCount(): Int
}