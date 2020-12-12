package br.svcdev.tripscheduler.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TicketAnyDayMonth(
    @Expose val success: Boolean,
    @Expose val error: String,
    @Expose val currency: String,
    @SerializedName("data") @Expose var mapData: MutableMap<String, Data>
) : Parcelable