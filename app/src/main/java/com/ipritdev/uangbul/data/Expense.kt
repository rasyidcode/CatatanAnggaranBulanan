package com.ipritdev.uangbul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(
    tableName = "expenses", foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
        ),
    ], indices = [Index(value = ["category_id"])]
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    val note: String?,
    val amount: Long,
    @ColumnInfo(name = "expensed_at")
    val expensedAt: Calendar,
    @ColumnInfo(name = "created_at")
    val createdAt: Calendar = Calendar.getInstance()
)
