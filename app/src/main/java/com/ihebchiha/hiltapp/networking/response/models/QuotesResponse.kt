package com.ihebchiha.hiltapp.networking.response.models

import android.os.Parcelable
import com.ihebchiha.hiltapp.networking.response.models.*
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuotesResponse (
	val id : Int,
	val date : String,
	val date_gmt : String,
	val guid : Guid,
	val modified : String,
	val modified_gmt : String,
	val slug : String,
	val status : String,
	val type : String,
	val link : String,
	val title : Title,
	val content : Content,
	val excerpt : Excerpt,
	val author : Int,
	val featured_media : Int,
	val comment_status : String,
	val ping_status : String,
	val sticky : Boolean,
	val template : String,
	val format : String,
	val meta : List<String>,
	val categories : List<Int>,
	val tags : List<String>,
	val _links : Links
) : Parcelable