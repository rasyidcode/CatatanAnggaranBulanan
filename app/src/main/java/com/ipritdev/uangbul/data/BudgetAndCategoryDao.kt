package com.ipritdev.uangbul.data

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

}