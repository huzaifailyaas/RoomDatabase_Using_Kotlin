package com.example.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.model.StudentTable


@Database(
    version = 1,
    entities =  [StudentTable::class]
)
abstract class StudentDB:RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object{
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
}