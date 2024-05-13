package com.example.geocacheexploreandroidapplication

import android.os.Parcelable
//import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parcelize

@Parcelize
data class Geocache(
    val id: Int? = null,
    val name: String,
    val description: String,
    val address: String
) : Parcelable
