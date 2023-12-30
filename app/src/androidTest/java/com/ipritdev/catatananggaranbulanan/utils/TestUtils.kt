package com.ipritdev.catatananggaranbulanan.utils

import com.ipritdev.catatananggaranbulanan.data.Budget
import com.ipritdev.catatananggaranbulanan.data.Category
import com.ipritdev.catatananggaranbulanan.data.Expense
import java.util.Calendar

val testCategories = listOf(
    Category(name = "Makan", icon = "ic_eat"),
    Category(name = "Laundry", icon = "ic_laundry"),
    Category(name = "Bensin", icon = "ic_gas"),
    Category(name = "Lain-lain", icon = "ic_others"),
)

private val budgetedAt = Calendar.getInstance().apply {
    setBudgetedAt(2024, Calendar.JANUARY)
}
val testBudgets = listOf(
    Budget(
        categoryId = 1,
        amount = 600000,
        budgetedAt = budgetedAt
    ),
    Budget(
        categoryId = 2,
        amount = 200000,
        budgetedAt = budgetedAt
    ),
    Budget(
        categoryId = 3,
        amount = 200000,
        budgetedAt = budgetedAt
    ),
    Budget(
        categoryId = 4,
        amount = 100000,
        budgetedAt = budgetedAt
    )
)

val testExpenses = listOf(
    Expense(
        categoryId = 1,
        note = "Makan mie ayam andalan anggarjaya",
        amount = 35000,
        expensedAt = Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 29, 12, 0, 0)
        }
    ),
    Expense(
        categoryId = 2,
        note = "Laundry ke-2",
        amount = 26000,
        expensedAt = Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 28, 10, 30, 0)
        }
    ),
    Expense(
        categoryId = 3,
        note = "Makan mie ayam andalan anggarjaya",
        amount = 35000,
        expensedAt = Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 29, 12, 0, 0)
        }
    ),
    Expense(
        categoryId = 1,
        note = "Makan mie ayam andalan anggarjaya",
        amount = 35000,
        expensedAt = Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 29, 12, 0, 0)
        }
    ),
)