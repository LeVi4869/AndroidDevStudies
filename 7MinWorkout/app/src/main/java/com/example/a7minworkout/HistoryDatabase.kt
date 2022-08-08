package com.example.a7minworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryEnitity::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao() : HistoryDao

    companion object{

        @Volatile
        private var INSTANCE : HistoryDatabase? = null

        fun getInstance(context: Context) : HistoryDatabase {
            //the lock/mutex thingy we learnt in OS class
            synchronized(this)  {
                var instance = INSTANCE

                if(instance == null)    {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "employee_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}