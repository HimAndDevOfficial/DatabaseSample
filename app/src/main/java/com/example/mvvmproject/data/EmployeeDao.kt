package com.example.mvvmproject.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmproject.model.Employee

@Dao
interface EmployeeDao {

    @Insert
   suspend fun addEmployee(employee: Employee)

    @Update
   suspend fun updateEmployee(employee: Employee)

    @Delete
   suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * from employee_table")
    fun readAllData() : LiveData<List<Employee>>

    @Query("SELECT * from employee_table WHERE id = :id")
    fun getEmployeeById(id:Int) : LiveData<Employee>

    @Query("SELECT * from employee_table WHERE firstName LIKE :name || '%'")
    fun getEmployeeById(name:String) : LiveData<List<Employee>>

    @Query("DELETE FROM employee_table")
    fun deleteAllEmployee()
}