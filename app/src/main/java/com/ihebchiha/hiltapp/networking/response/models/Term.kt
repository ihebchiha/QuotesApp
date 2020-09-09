package com.ihebchiha.hiltapp.networking.response.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Term (
	val taxonomy : String,
	val embeddable : Boolean,
	val href : String
) : Parcelable