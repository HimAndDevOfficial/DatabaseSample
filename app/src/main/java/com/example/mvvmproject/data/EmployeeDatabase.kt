package com.example.mvvmproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmproject.model.Employee

@Database(
    entities = [Employee::class],
    version = 1,
    exportSchema = true
)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao() :EmployeeDao

    companion object {

        @Volatile
        private lateinit var instance:EmployeeDatabase

        fun getDatabase() : EmployeeDatabase {

            if(::instance.isInitialized)
                return instance

            throw UninitializedPropertyAccessException()
        }

        fun initializeDatabase(context: Context) {

            synchronized(this)  {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employee_database"
                ).build()

            }
        }
    }
}

/*
    val tempInstance = INSTANCE

           if(tempInstance!= null)
           {
               return tempInstance
           }
            synchronized(this)  {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employee_database"
                ).build()
                INSTANCE = instance
                return instance
            }
 */