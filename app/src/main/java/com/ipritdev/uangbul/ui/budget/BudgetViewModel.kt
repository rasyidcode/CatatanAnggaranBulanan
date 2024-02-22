package com.ipritdev.uangbul.ui.budget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.ipritdev.uangbul.data.Budget
import com.ipritdev.uangbul.data.BudgetAndCategory
import com.ipritdev.uangbul.data.BudgetRepository
import com.ipritdev.uangbul.utils.toIDR
import kotlinx.coroutines.launch
import java.util.Calendar

class BudgetViewModel(
    private val budgetRepository: BudgetRepository
) : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedMonth = MutableLiveData<Calendar>()
    val selectedMonthString: LiveData<String> = _selectedMonth.map {
        val month: String = when (it.get(Calendar.MONTH)) {
            Calendar.JANUARY -> "Jan"
            Calendar.FEBRUARY -> "Feb"
            Calendar.MARCH -> "Mar"
            Calendar.APRIL -> "Apr"
            Calendar.MAY -> "May"
            Calendar.JUNE -> "Jun"
            Calendar.JULY -> "Jul"
            Calendar.AUGUST -> "Aug"
            Calendar.SEPTEMBER -> "Sep"
            Calendar.OCTOBER -> "Oct"
            Calendar.NOVEMBER -> "Nov"
            Calendar.DECEMBER -> "Dec"
            else -> ""
        }

        "$month ${it.get(Calendar.YEAR)}"
    }
    val selectedMonth: LiveData<Calendar> = _selectedMonth

    private val _budgets = MutableLiveData<List<BudgetAndCategory>>()
    val budgets: LiveData<List<BudgetAndCategory>> = budgetRepository.getBudgetAndCategoryList(
        _selectedMonth.value ?: DEFAULT_CALENDAR
    )
    val budgetTotal: LiveData<String> = budgets.map {
        it.sumOf { budgetAndCategory -> budgetAndCategory.budget.amount }.toIDR()
    }
    val budgetUsed: LiveData<String> = budgets.map {
        ""
    }

    val budgetAndCategoryList: LiveData<List<BudgetAndCategory>> =
        budgetRepository.budgetAndCategoryList


    init {
        setSelectedMonth(DEFAULT_CALENDAR)
    }

    fun setToPreviousMonth() {
        _selectedMonth.value = _selectedMonth.value.apply {
            this?.add(Calendar.MONTH, -1)
        }
    }

    fun setToNextMonth() {
        _selectedMonth.value = _selectedMonth.value.apply {
            this?.add(Calendar.MONTH, 1)
        }
    }

    fun setSelectedMonth(calendar: Calendar) {
        _selectedMonth.value = calendar
    }

    fun addNewBudget(
        categoryId: Int,
        amount: Long,
        budgetedAt: Calendar
    ) = viewModelScope.launch {
        budgetRepository.createBudget(
            Budget(
                categoryId = categoryId,
                amount = amount,
                budgetedAt = budgetedAt
            )
        )
    }

    fun editBudget(
        budgetId: Int,
        categoryId: Int,
        amount: Long,
        budgetedAt: Calendar
    ) = viewModelScope.launch {
        budgetRepository.updateBudget(
            Budget(
                id = budgetId,
                categoryId = categoryId,
                amount = amount,
                budgetedAt = budgetedAt
            )
        )
    }

    fun deleteBudget(budgetId: Int) = viewModelScope.launch {
        budgetRepository.deleteBudget(budgetId)
    }

    private fun setBudgets() {
        val budgets = _selectedMonth.value?.let { budgetRepository.getBudgetAndCategoryList(it) }
    }

    class BudgetViewModelFactory(
        private val budgetRepository: BudgetRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BudgetViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BudgetViewModel(budgetRepository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private val DEFAULT_CALENDAR = Calendar.getInstance()
    }
}