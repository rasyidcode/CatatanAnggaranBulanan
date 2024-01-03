package com.ipritdev.uangbul.data

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class BudgetTestDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var budgetTestDao: BudgetTestDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

        budgetTestDao = db.budgetTestDao()
        budgetTestDao.insertBudgets(
            listOf(
                BudgetTest(name = "Makan", createdAt = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2023)
                    set(Calendar.MONTH, Calendar.DECEMBER)
                    set(Calendar.DATE, 1)
                }.timeInMillis / 1000),
                BudgetTest(name = "Makan", createdAt = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2023)
                    set(Calendar.MONTH, Calendar.MARCH)
                    set(Calendar.DATE, 1)
                }.timeInMillis / 1000),
                BudgetTest(name = "Makan", createdAt = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2022)
                    set(Calendar.MONTH, Calendar.JANUARY)
                    set(Calendar.DATE, 1)
                }.timeInMillis / 1000),
                BudgetTest(name = "Makan", createdAt = Calendar.getInstance().apply {
                    set(Calendar.YEAR, 2021)
                    set(Calendar.MONTH, Calendar.JULY)
                    set(Calendar.DATE, 1)
                }.timeInMillis / 1000),
            )
        )
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_getBudgets() {
//        val budgets = budgetTestDao.getAllBudget()
        val budgets = budgetTestDao.getBudget()
        val budgets2 = budgetTestDao.getBudget2(Calendar.getInstance().apply {
            set(Calendar.YEAR, 2022)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
        }.timeInMillis / 1000)

//        Log.d("test_getBudgets", "budgets: $budgets")
        Log.d("test_getBudgets", "budgets: $budgets")
        Log.d("test_getBudgets", "budgets2: $budgets2")

        assertThat(budgets.size, equalTo(4))
        assertThat(budgets2.size, equalTo(1))
    }
}