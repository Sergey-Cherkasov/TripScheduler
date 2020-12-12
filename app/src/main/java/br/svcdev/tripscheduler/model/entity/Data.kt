package br.svcdev.tripscheduler.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    @Expose val origin: String,
    @Expose val destination: String,
    @Expose val price: Int,
    @Expose val transfers: Int,
    @Expose val airline: String,
    @Expose val flightNumber: Int,
    @Expose val departureAt: String,
    @Expose val returnAt: String,
    @Expose val expiresAt: String
) : Parcelable
