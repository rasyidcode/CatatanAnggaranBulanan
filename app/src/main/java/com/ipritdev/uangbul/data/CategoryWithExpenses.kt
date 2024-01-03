package com.ipritdev.uangbul.data

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithExpenses(
    @Embedded
    val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val expenses: List<Expense>
)
