package br.svcdev.tripscheduler.presenter.interfaces

import br.svcdev.tripscheduler.view.interfaces.IItemView

interface IListPresenter<I : IItemView> {
    var itemClickListener: ((I) -> Unit)?
    var itemLongClickListener: ((I) -> Unit)?
    fun bindView(view: I)
    fun getCount(): Int
}