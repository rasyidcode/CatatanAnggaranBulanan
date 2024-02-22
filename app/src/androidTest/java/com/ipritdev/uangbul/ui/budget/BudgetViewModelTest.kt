package com.ipritdev.uangbul.ui.budget

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.ipritdev.uangbul.data.AppDatabase
import com.ipritdev.uangbul.data.BudgetRepository
import com.ipritdev.uangbul.utils.testBudgets
import com.ipritdev.uangbul.utils.testCategories
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Calendar

class BudgetViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: BudgetViewModel

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        val categoryDao = appDatabase.categoryDao()
        val budgetDao = appDatabase.budgetDao()
        val budgetAndCategoryDao = appDatabase.budgetAndCategoryDao()
        runBlocking {
            categoryDao.insertCategories(testCategories)
            budgetDao.insertBudgets(testBudgets)
        }
        val budgetRepository = BudgetRepository(budgetDao, budgetAndCategoryDao)
        viewModel = BudgetViewModel(budgetRepository)

    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun test_selectedMonth() {
        viewModel.selectedMonth.observeForever { }

        viewModel.setSelectedMonth(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
        })

        assertThat("Jan 2024", equalTo(viewModel.selectedMonth.value))
    }

    @Test
    fun test_setToPrevMonth() {
        viewModel.selectedMonth.observeForever { }

        viewModel.setSelectedMonth(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
        })
        viewModel.setToPreviousMonth()

        assertThat("Dec 2023", equalTo(viewModel.selectedMonth.value))
    }

    @Test
    fun test_setToNextMonth() {
        viewModel.selectedMonth.observeForever { }

        viewModel.setSelectedMonth(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
        })
        viewModel.setToNextMonth()

        assertThat("Feb 2024", equalTo(viewModel.selectedMonth.value))
    }

    @Test
    fun test_budgetTotal() {
        viewModel.budgetTotal.observeForever { }

        assertThat("Rp1.100.000,00", equalTo(viewModel.budgetTotal.value))
    }

    @Test
    fun test_

}