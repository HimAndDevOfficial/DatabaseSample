package com.example.mvvmproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName="employee_table")
data class Employee(

   @PrimaryKey(autoGenerate = true) val id:Int,
    val firstName:String,
    val lastName: String,
    val age:Int
    ):Parcelable