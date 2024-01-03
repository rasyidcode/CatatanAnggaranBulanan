package com.ipritdev.uangbul.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(
    tableName = "budgets", foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
        ),
    ], indices = [Index(value = ["category_id"])]
)
data class Budget(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    val amount: Long,
    @ColumnInfo(name = "budgeted_at")
    val budgetedAt: Calendar,
    @ColumnInfo(name = "created_at")
    val createdAt: Calendar = Calendar.getInstance().apply {
        set(Calendar.MILLISECOND, 0)
    }
) {

    val year
        get() = budgetedAt.get(Calendar.YEAR)

    val month
        get() = budgetedAt.get(Calendar.MONTH)

    override fun toString(): String {
        return "Budget[id=${id}, categoryId=${categoryId}, amount=${amount}, budgetedAt=${budgetedAt.time}, createdAt=${createdAt.time}]"
    }

}
