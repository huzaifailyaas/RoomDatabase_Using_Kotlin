package com.example.roomdatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "StudentTable")
data class StudentTable(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var firstname: String,
    var lastname: String,
    var age: Int
) : Parcelable
