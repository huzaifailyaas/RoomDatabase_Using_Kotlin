package com.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.data.StudentDao
import com.example.roomdatabase.model.StudentTable

class UserRepository(private val studentDao: StudentDao) {

    val readAllData: LiveData<List<StudentTable>> = studentDao.readAllData()

     fun addUser(studentTable: StudentTable) {
        studentDao.addStudent(studentTable)
    }

    suspend fun updateUser(user: StudentTable) {
        studentDao.updateUser(user)
    }

    suspend fun deleteUser(user: StudentTable) {
        studentDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        studentDao.deleteAllUsers() // This should now be correct
    }

    fun searchDatabase(searchQuery: String): LiveData<List<StudentTable>> {
        return studentDao.searchDatabase(searchQuery)
    }
}
