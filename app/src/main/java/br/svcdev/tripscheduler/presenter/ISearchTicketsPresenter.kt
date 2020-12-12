package br.svcdev.tripscheduler.presenter

interface ISearchTicketsPresenter : IPresenter {
    var buttonClickListener: ((String, String, String) -> Unit)
    var textChangeListener: ((String) -> Unit)
}