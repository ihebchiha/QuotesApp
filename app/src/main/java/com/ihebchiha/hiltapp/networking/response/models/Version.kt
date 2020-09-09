package com.ihebchiha.hiltapp.networking.response.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Version(
	val count : Int,
	val href : String
) : Parcelable