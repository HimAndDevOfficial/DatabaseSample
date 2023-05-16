package com.example.mvvmproject.viewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.mvvmproject.model.Employee
import com.example.mvvmproject.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//The Viewmodel class is used to store and manage UI related data in lifecycle conscious manner
//The Viewmodel survives configuration change such as screen rotation
//EmployeeViewModel provides user employee data to the UI and it also survive configuration change. act as communicator between repository and UI

//Live Data is an observable data holder class

class EmployeeViewModel : ViewModel() {

    private lateinit var repository : EmployeeRepository
    var readAllData:LiveData<List<Employee>>


    init {
       repository = EmployeeRepository()
        readAllData  = repository.readAllData
    }
    fun addEmployee(employee:Employee) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employee)
        }
    }

    suspend fun updateEmployee(employee: Employee)
    {
         repository.updateEmployee(employee)
    }

   suspend fun deleteUser(employee: Employee) {
       repository.deleteEmployee(employee)
    }

    suspend fun searchEmployee(x: Int) : LiveData<Employee> {
       return repository.searchEmployeeById(x)
    }
}