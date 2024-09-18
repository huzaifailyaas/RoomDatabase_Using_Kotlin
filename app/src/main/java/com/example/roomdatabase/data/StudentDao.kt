package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.model.StudentTable

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addStudent(studentTable: StudentTable)

    @Update
    suspend fun updateUser(user: StudentTable)

    @Delete
    suspend fun deleteUser(user: StudentTable)

    @Query("DELETE FROM StudentTable") // Changed to @Query for deleting all records
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM StudentTable ORDER BY id ASC")
    fun readAllData(): LiveData<List<StudentTable>>

    @Query("SELECT * FROM StudentTable WHERE firstname LIKE '%' || :searchQuery || '%' OR lastname LIKE '%' || :searchQuery || '%'")
    fun searchDatabase(searchQuery: String): LiveData<List<StudentTable>>

}
