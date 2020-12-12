package br.svcdev.tripscheduler.model.entity.cityairport

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IataCode(
    @Expose val code: String,
    @Expose val name: String
) : Parcelable