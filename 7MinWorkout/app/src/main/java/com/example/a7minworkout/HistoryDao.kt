package com.example.a7minworkout

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity : HistoryEnitity)
}