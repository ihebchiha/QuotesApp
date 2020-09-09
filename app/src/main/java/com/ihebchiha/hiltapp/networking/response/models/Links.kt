package com.ihebchiha.hiltapp.networking.response.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links (
	val self : List<Self>,
	val collection : List<Collection>,
	val about : List<About>,
	val author : List<Author>,
	val replies : List<Replies>,
	val version : List<Version>,
	val predecessor : List<Predecessor>,
	val attachment : List<Attachment>,
	val term : List<Term>,
	val curies : List<Curies>
) : Parcelable