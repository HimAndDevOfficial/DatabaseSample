package com.example.mvvmproject.repository

import androidx.lifecycle.LiveData
import com.example.mvvmproject.data.EmployeeDatabase
import com.example.mvvmproject.model.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Repository act as intermediator between your Viewmodel and multiple data source, it has access to multiple data sources

class EmployeeRepository {


    private val database:EmployeeDatabase by lazy {
        EmployeeDatabase.getDatabase()
    }

    val readAllData : LiveData<List<Employee>> =database.employeeDao().readAllData()
    suspend fun addEmployee(employee: Employee)
    {
        database.employeeDao().addEmployee(employee)
    }
    suspend fun updateEmployee(employee: Employee)
    {
        database.employeeDao().updateEmployee(employee)
    }
    suspend fun deleteEmployee(employee: Employee)
    {
        database.employeeDao().deleteEmployee(employee)
    }

    suspend fun searchEmployeeById(x:Int) : LiveData<Employee>
    {
       return withContext(Dispatchers.IO) {
           database.employeeDao().getEmployeeById(x)
       }

    }
}