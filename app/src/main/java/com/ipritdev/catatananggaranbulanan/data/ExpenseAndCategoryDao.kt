package com.ipritdev.catatananggaranbulanan.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ExpenseAndCategoryDao {
    @Query("SELECT expenses.id, categories.name, categories.icon, expenses.note, " +
            "expenses.amount, expenses.expensed_at, expenses.created_at " +
            "FROM expenses, categories " +
            "WHERE expenses.category_id = categories.id " +
            "AND strftime(\"%Y-%m\", date(expenses.expensed_at, \"epochtime\")) = strftime(\"%Y-%m\", date(\"now\"))")
    fun getCurrentMonthExpensesWithCategory(): LiveData<List<ExpenseAndCategory>>

    @Query("SELECT expenses.id, categories.name, categories.icon, expenses.note, " +
            "expenses.amount, expenses.expensed_at, expenses.created_at " +
            "FROM expenses, categories " +
            "WHERE expenses.category_id = categories.id " +
            "AND strftime(\"%Y-%m\", date(expenses.expensed_at, \"epochtime\")) = strftime(\"%Y-%m\", date(:monthTimestamp, \"epochtime\"))")
    fun getMonthExpensesWithCategoryByMonthTimestamp(monthTimestamp: Long): LiveData<List<ExpenseAndCategory>>

    @Query("SELECT expenses.id, categories.name, categories.icon, expenses.note, " +
            "expenses.amount, expenses.expensed_at, expenses.created_at " +
            "FROM expenses, categories " +
            "WHERE expenses.category_id = categories.id " +
            "AND strftime(\"%Y\", date(expenses.expensed_at, \"epochtime\")) = strftime(\"%Y\", date(\"now\"))")
    fun getCurrentYearExpenses(): LiveData<List<ExpenseAndCategory>>
}