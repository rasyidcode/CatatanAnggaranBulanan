package com.ipritdev.uangbul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetTest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    val createdAtString: String? = null,
)
