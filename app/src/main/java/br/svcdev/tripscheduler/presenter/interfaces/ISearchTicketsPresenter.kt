package br.svcdev.tripscheduler.presenter.interfaces

interface ISearchTicketsPresenter : IPresenter {
    var buttonClickListener: ((String, String, String) -> Unit)
}