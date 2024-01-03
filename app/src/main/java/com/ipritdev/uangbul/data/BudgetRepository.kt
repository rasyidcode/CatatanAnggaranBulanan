package com.ipritdev.uangbul.data

class BudgetRepository {

    fun getBudgets() = BudgetContent.ITEMS

    companion object {
        private var instance: BudgetRepository = BudgetRepository()

        fun getInstance(): BudgetRepository = instance

    }
}