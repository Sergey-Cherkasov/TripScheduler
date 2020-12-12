package br.svcdev.tripscheduler.common

interface IBackPressedListener {
    fun onBackPressed(): Boolean
}