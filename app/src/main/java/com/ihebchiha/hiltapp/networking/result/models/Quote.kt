package com.ihebchiha.hiltapp.networking.result.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(@PrimaryKey val id: Int, val date: String, val author: String, val content: String)