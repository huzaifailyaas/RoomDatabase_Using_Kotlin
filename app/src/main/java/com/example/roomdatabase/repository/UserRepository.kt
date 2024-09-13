package com.example.roomdatabase.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabase.data.StudentDao
import com.example.roomdatabase.model.StudentTable

class UserRepository(private val studentDao: StudentDao) {

    val readAllData: LiveData<List<StudentTable>> = studentDao.readAllData()

    suspend fun addUser(studentTable: StudentTable) {
        studentDao.addStudent(studentTable)
    }

    suspend fun updateUser(user: StudentTable) {
        studentDao.updateUser(user) // Use the instance variable
    }

    suspend fun deleteUser(user: StudentTable){
        studentDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        studentDao.deleteAllUsers()
    }
}
