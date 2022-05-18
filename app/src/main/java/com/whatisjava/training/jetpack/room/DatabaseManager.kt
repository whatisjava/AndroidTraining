package com.whatisjava.training.jetpack.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.whatisjava.training.jetpack.room.dao.UserDao
import com.whatisjava.training.jetpack.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseManager? = null

        fun getInstance(context: Context): DatabaseManager {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance: DatabaseManager = Room.databaseBuilder(context.applicationContext, DatabaseManager::class.java, "test_db").build()
                INSTANCE = instance
                return INSTANCE as DatabaseManager
            }
        }

    }
}