package com.ipritdev.uangbul.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ipritdev.uangbul.data.Budget
import com.ipritdev.uangbul.data.BudgetRepository

class BudgetViewModel : ViewModel() {

    private val _budgets = MutableLiveData<List<Budget>>()
    val budgets: LiveData<List<Budget>> = _budgets

    init {
        _budgets.value = BudgetRepository.getInstance().getBudgets()
    }
}