package com.ipritdev.catatananggaranbulanan.data

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ipritdev.catatananggaranbulanan.utils.getValue
import com.ipritdev.catatananggaranbulanan.utils.setExpensedAt
import com.ipritdev.catatananggaranbulanan.utils.testCategories
import com.ipritdev.catatananggaranbulanan.utils.testExpenses
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.Matchers.nullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class ExpenseDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var expenseDao: ExpenseDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        expenseDao = db.expenseDao()

        runBlocking {
            db.categoryDao().insertCategories(testCategories)
            expenseDao.insertExpenses(testExpenses)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_getExpenses() {
        val expenses = getValue(expenseDao.getExpenses())

        assertThat(expenses.size, equalTo(4))
    }

    @Test
    fun test_getExpenseById() {
        val expense = getValue(expenseDao.getExpenseById(1))

        assertThat(expense, notNullValue())
        assertThat(expense?.id, equalTo(1))
        assertThat(expense?.categoryId, equalTo(1))
        assertThat(expense?.amount, equalTo(35000))
        assertThat(expense?.note, equalTo("Makan mie ayam andalan anggarjaya"))
        assertThat(expense?.expensedAt, equalTo(Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 29, 12, 0, 0)
        }))
    }

    @Test
    fun test_insertNewExpense() = runBlocking {
        expenseDao.insertExpense(
            Expense(
                categoryId = 1,
                amount = 32000,
                note = "Makan sabana",
                expensedAt = Calendar.getInstance().apply {
                    setExpensedAt(2023, Calendar.DECEMBER, 29, 13, 0, 0)
                }
            )
        )

        val expense = getValue(expenseDao.getExpenseById(5))

        assertThat(expense, notNullValue())
        assertThat(expense?.id, equalTo(5))
        assertThat(expense?.categoryId, equalTo(1))
        assertThat(expense?.amount, equalTo(32000))
        assertThat(expense?.note, equalTo("Makan sabana"))
        assertThat(expense?.expensedAt, equalTo(Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 29, 13, 0, 0)
        }))
    }

    @Test
    fun test_insertExpenses() = runBlocking {
        expenseDao.insertExpenses(
            listOf(
                Expense(
                    categoryId = 1,
                    note = "Nasi goreng 10rb",
                    amount = 20000,
                    expensedAt = Calendar.getInstance().apply {
                        setExpensedAt(2023, Calendar.DECEMBER, 29, 23, 0, 0)
                    }
                ),
                Expense(
                    categoryId = 1,
                    note = "Nasi goreng 10rb",
                    amount = 20000,
                    expensedAt = Calendar.getInstance().apply {
                        setExpensedAt(2023, Calendar.DECEMBER, 30, 23, 0, 0)
                    }
                ),
                Expense(
                    categoryId = 1,
                    note = "Nasi goreng 10rb",
                    amount = 20000,
                    expensedAt = Calendar.getInstance().apply {
                        setExpensedAt(2023, Calendar.DECEMBER, 31, 23, 0, 0)
                    }
                )
            )
        )

        val expenses = getValue(expenseDao.getExpenses())

        assertThat(expenses.size, equalTo(7))
    }

    @Test
    fun test_updateExpense() = runBlocking {
        expenseDao.updateExpense(
            Expense(
                id = 1,
                categoryId = 3,
                note = "Beli bensin di pom mini",
                amount = 20000,
                expensedAt = Calendar.getInstance().apply {
                    setExpensedAt(2023, Calendar.DECEMBER, 28, 14, 0, 0)
                }
            )
        )

        val expense = getValue(expenseDao.getExpenseById(1))

        assertThat(expense, notNullValue())
        assertThat(expense?.id, equalTo(1))
        assertThat(expense?.categoryId, equalTo(3))
        assertThat(expense?.note, equalTo("Beli bensin di pom mini"))
        assertThat(expense?.amount, equalTo(20000))
        assertThat(expense?.expensedAt, equalTo(Calendar.getInstance().apply {
            setExpensedAt(2023, Calendar.DECEMBER, 28, 14, 0, 0)
        }))
    }

    @Test
    fun test_deleteExpense() = runBlocking {
        expenseDao.deleteExpense(
            Expense(
                id = 1,
                categoryId = 1,
                note = "Makan mie ayam andalan anggarjaya",
                amount = 35000,
                expensedAt = Calendar.getInstance().apply {
                    set(2023, 11, 29, 12, 0, 0)
                }
            )
        )

        val expense = getValue(expenseDao.getExpenseById(1))

        assertThat(expense, nullValue())
    }

    @Test
    fun test_clearExpenses() = runBlocking {
        expenseDao.clearExpenses()

        assertThat(getValue(expenseDao.getExpenses()), empty())
    }
}