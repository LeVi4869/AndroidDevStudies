package com.example.a7minworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="history-table")
data class HistoryEnitity(
    @PrimaryKey
    val date : String
)
