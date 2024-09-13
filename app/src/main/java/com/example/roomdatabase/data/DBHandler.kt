package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Room

object DBHandler {
    @Volatile
    private var INSTANCE: StudentDB? = null

    fun getDB(context: Context): StudentDB {
        // Double-checked locking to ensure thread safety
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                StudentDB::class.java,
                "StudentDB"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}
