package com.example.mvvmproject

import android.app.Application
import android.content.Context
import androidx.room.util.joinIntoString
import com.example.mvvmproject.data.EmployeeDatabase

class MvvmProjectApp : Application() {

    companion object {
        private lateinit var instance: MvvmProjectApp

        fun getContext() : Context {

            if(::instance.isInitialized) {
                return instance.applicationContext
            }
            throw UninitializedPropertyAccessException()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance=this // initialize

        EmployeeDatabase.initializeDatabase(applicationContext)
    }
}