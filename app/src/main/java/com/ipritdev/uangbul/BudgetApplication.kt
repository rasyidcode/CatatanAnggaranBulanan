package com.ipritdev.uangbul

import android.app.Application
import com.ipritdev.uangbul.data.AppDatabase
import com.ipritdev.uangbul.data.BudgetRepository

class BudgetApplication : Application() {

    private val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    val budgetRepository: BudgetRepository by lazy { BudgetRepository(database.budgetDao()) }

}