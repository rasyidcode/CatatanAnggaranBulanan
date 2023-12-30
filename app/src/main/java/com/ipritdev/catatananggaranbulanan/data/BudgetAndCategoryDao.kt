package com.ipritdev.catatananggaranbulanan.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface BudgetAndCategoryDao {

    @Transaction
    @Query("SELECT * FROM budgets")
    fun getListBudgetAndCategory(): LiveData<List<BudgetAndCategory>>

    @Transaction
    @Query("SELECT * FROM budgets WHERE budgeted_at = :budgetedAt")
    fun getListBudgetAndCategory(budgetedAt: Long): LiveData<List<BudgetAndCategory>>

    //    @Query("SELECT budgets.id, categories.name, categories.icon, " +
//            "budgets.amount, budgets.budgeted_at AS budgetedAt, " +
//            "budgets.created_at AS createdAt " +
//            "FROM budgets, categories "+
//            "WHERE budgets.category_id = categories.id " +
//            "AND strftime(\"%Y-%m\", date(budgets.budgeted_at, \"epochtime\")) = strftime(\"%Y-%m\", date(\"now\"))")
//    fun getCurrentMonthBudgetWithCategory(): LiveData<List<BudgetAndCategory>>
//
//    @Query("SELECT budgets.id, categories.name, categories.icon, " +
//            "budgets.amount, budgets.budgeted_at AS budgetedAt, " +
//            "budgets.created_at AS createdAt " +
//            "FROM budgets, categories "+
//            "WHERE budgets.category_id = categories.id " +
//            "AND strftime(\"%Y-%m\", date(budgets.budgeted_at, \"epochtime\")) = strftime(\"%Y-%m\", date(\"now\"))")
//    fun getCurrentMonthBudgetWithCategoryByMonthTimestamp(): LiveData<List<BudgetAndCategory>>
//    @Query("SELECT budgets.id, categories.name, budgets.amount, budgets.budgeted_at AS time FROM budgets JOIN categories ON budgets.category_id = categories.id")
//    fun getBudgets(): List<Budget2>

}