package com.ihebchiha.hiltapp.networking.response.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Excerpt (
	val rendered : String,
	val protected : Boolean
) : Parcelable