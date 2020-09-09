package com.ihebchiha.hiltapp.networking.response.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Curies (
	val name : String,
	val href : String,
	val templated : Boolean
): Parcelable