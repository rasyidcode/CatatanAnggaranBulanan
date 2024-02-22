package com.ipritdev.uangbul.data

import androidx.lifecycle.LiveData
import com.ipritdev.uangbul.utils.setBudgetedAt
import java.util.Calendar

class BudgetRepository(
    private val budgetDao: BudgetDao,
    private val budgetAndCategoryDao: BudgetAndCategoryDao
) {

    val budgets: LiveData<List<Budget>> = budgetDao.getBudgets()

    val budgetAndCategoryList: LiveData<List<BudgetAndCategory>> =
        budgetAndCategoryDao.getListBudgetAndCategory()

    fun getBudgetAndCategoryList(calendar: Calendar): LiveData<List<BudgetAndCategory>> =
        budgetAndCategoryDao.getListBudgetAndCategory(
            calendar.apply {
                setBudgetedAt(get(Calendar.YEAR), get(Calendar.MONTH))
            }.timeInMillis
        )

    suspend fun createBudget(budget: Budget) = budgetDao.insertBudget(budget)

    suspend fun updateBudget(budget: Budget) = budgetDao.updateBudget(budget)

    suspend fun deleteBudget(budget: Budget) = budgetDao.deleteBudget(budget)

    suspend fun deleteBudget(budgetId: Int) = budgetDao.deleteBudget(budgetId)

    companion object {
        @Volatile
        private var instance: BudgetRepository? = null

        fun getInstance(
            budgetDao: BudgetDao,
            budgetAndCategoryDao: BudgetAndCategoryDao
        ): BudgetRepository = instance ?: synchronized(this) {
            instance ?: BudgetRepository(budgetDao, budgetAndCategoryDao).also { instance = it }
        }

    }
}