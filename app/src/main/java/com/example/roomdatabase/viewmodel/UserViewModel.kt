package com.example.roomdatabase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase.data.StudentDB
import com.example.roomdatabase.model.StudentTable
import com.example.roomdatabase.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    var  readAllData: LiveData<List<StudentTable>>
    private  var repository: UserRepository

    init {
        val studentDao= StudentDB.getDB(application).studentDao()
        repository= UserRepository(studentDao)
        readAllData=repository.readAllData
    }

    fun addUser(studentTable: StudentTable){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(studentTable)
        }
    }

    fun updateUser(user:StudentTable){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: StudentTable){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
}