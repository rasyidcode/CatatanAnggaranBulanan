package com.ipritdev.catatananggaranbulanan.data

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetAndCategory(
    @Embedded val budget: Budget,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: Category
)