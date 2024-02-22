package com.ipritdev.uangbul.data

class ExpenseRepository(
    private val expenseDao: ExpenseDao
) {

    companion object {
        @Volatile
        private var instance: ExpenseRepository? = null

        fun getInstance(expenseDao: ExpenseDao): ExpenseRepository =
            instance ?: synchronized(this) {
                instance ?: ExpenseRepository(expenseDao).also { instance = it }
            }

    }
}