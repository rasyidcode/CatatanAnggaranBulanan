package com.ipritdev.catatananggaranbulanan.data

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseAndCategory(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val expense: Expense
)