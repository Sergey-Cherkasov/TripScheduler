package br.svcdev.tripscheduler.presenter

import br.svcdev.tripscheduler.model.entity.cityairport.IataCode

interface IAutoCompletePresenter {
    fun getIataCodes(query: String): MutableList<IataCode>
}