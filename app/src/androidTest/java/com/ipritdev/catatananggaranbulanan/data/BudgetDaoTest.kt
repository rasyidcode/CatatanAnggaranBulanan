package com.ipritdev.catatananggaranbulanan.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ipritdev.catatananggaranbulanan.utils.getValue
import com.ipritdev.catatananggaranbulanan.utils.setBudgetedAt
import com.ipritdev.catatananggaranbulanan.utils.testBudgets
import com.ipritdev.catatananggaranbulanan.utils.testCategories
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.Matchers.empty
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class BudgetDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var budgetDao: BudgetDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

        budgetDao = db.budgetDao()

        db.categoryDao().insertCategories(testCategories)
        budgetDao.insertBudgets(testBudgets)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_getBudgets() {
        val budgets = getValue(budgetDao.getBudgets())

        assertThat(budgets.size, equalTo(4))
        assertThat(budgets.first().id, equalTo(1))
        assertThat(budgets.first().categoryId, equalTo(1))
        assertThat(budgets.first().amount, equalTo(600000))
        assertThat(budgets.first().year, equalTo(2024))
        assertThat(budgets.first().month, equalTo(Calendar.JANUARY))
        assertThat(budgets.first().budgetedAt, equalTo(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }))
    }

    @Test
    fun test_getBudgetById() {
        val budget = getValue(budgetDao.getBudgetById(1))

        assertThat(budget, notNullValue())
        assertThat(budget?.id, equalTo(1))
        assertThat(budget?.categoryId, equalTo(1))
        assertThat(budget?.amount, equalTo(600000))
        assertThat(budget?.budgetedAt, equalTo(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }))
    }

    @Test
    fun test_insertNewBudget() = runBlocking {
        budgetDao.insertBudget(
            Budget(
                categoryId = 1,
                amount = 1000000,
                budgetedAt = Calendar.getInstance().apply {
                    setBudgetedAt(2023, Calendar.DECEMBER)
                }
            )
        )

        val budget = getValue(budgetDao.getBudgets()).last()

        assertThat(budget.id, equalTo(5))
        assertThat(budget.categoryId, equalTo(1))
        assertThat(budget.amount, equalTo(1000000))
        assertThat(budget.year, equalTo(2023))
        assertThat(budget.month, equalTo(Calendar.DECEMBER))
        assertThat(budget.budgetedAt, equalTo(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.DECEMBER)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }))
    }

    @Test
    fun test_insertBudgets() = runBlocking {
        budgetDao.insertBudgets(
            listOf(
                Budget(
                    categoryId = 1,
                    amount = 500000,
                    budgetedAt = Calendar.getInstance().apply {
                        setBudgetedAt(2023, Calendar.DECEMBER)
                    }
                ),
                Budget(
                    categoryId = 1,
                    amount = 450000,
                    budgetedAt = Calendar.getInstance().apply {
                        setBudgetedAt(2023, Calendar.NOVEMBER)
                    }
                ),
                Budget(
                    categoryId = 1,
                    amount = 400000,
                    budgetedAt = Calendar.getInstance().apply {
                        setBudgetedAt(2023, Calendar.OCTOBER)
                    }
                ),
            )
        )

        val budgets = getValue(budgetDao.getBudgets())

        assertThat(budgets.size, equalTo(7))
    }

    @Test
    fun test_updateBudget() = runBlocking {
        budgetDao.updateBudget(
            Budget(
                id = 1,
                categoryId = 2,
                amount = 1250000,
                budgetedAt = Calendar.getInstance().apply {
                    setBudgetedAt(2023, Calendar.FEBRUARY)
                }
            )
        )

        val updatedBudget = getValue(budgetDao.getBudgetById(1))

        assertThat(updatedBudget, notNullValue())
        assertThat(updatedBudget?.categoryId, equalTo(2))
        assertThat(updatedBudget?.amount, equalTo(1250000))
        assertThat(updatedBudget?.year, equalTo(2023))
        assertThat(updatedBudget?.month, equalTo(Calendar.FEBRUARY))
        assertThat(updatedBudget?.budgetedAt, equalTo(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2023)
            set(Calendar.MONTH, Calendar.FEBRUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }))
    }

    @Test
    fun test_deleteBudget() = runBlocking {
        val budgetedAt = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, 1)
        }
        budgetDao.deleteBudget(
            Budget(
                id = 1,
                categoryId = 1,
                amount = 600000,
                budgetedAt = budgetedAt
            ),
        )

        val deletedBudget = getValue(budgetDao.getBudgetById(1))

        assertThat(deletedBudget, nullValue())
    }

    @Test
    fun test_clearBudgets() = runBlocking {
        budgetDao.clearBudgets()

        val budgets = getValue(budgetDao.getBudgets())

        assertThat(budgets, empty())
    }

}