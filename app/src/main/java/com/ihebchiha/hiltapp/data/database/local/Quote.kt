package com.ihebchiha.hiltapp.data.database.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Immutable model class for a Task. In order to compile with Room, we can't use @JvmOverloads to
 * generate multiple constructors.
 *
 * @param title title of the task
 * @param description description of the task
 * @param isCompleted whether or not this task is completed
 * @param id id of the task
 */
//@Entity(tableName = "quote")
//data class Quote @JvmOverloads constructor(
//    @ColumnInfo(name = "date") var date:String  = "",
//    @ColumnInfo(name = "content") var content: String = "",
//    @ColumnInfo(name = "author") var author: String = "",
//    @PrimaryKey @ColumnInfo(name = "id") var id: Int
//) {
//
//
//    val isEmpty
//        get() = content.isEmpty()
//}