package com.example.a7minworkout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity : HistoryEnitity)

    @Query("SELECT * FROM `history-table`")
    fun fetchAllDates() : Flow<List<HistoryEnitity>>
}