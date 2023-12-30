package com.ipritdev.catatananggaranbulanan.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ipritdev.catatananggaranbulanan.utils.getValue
import com.ipritdev.catatananggaranbulanan.utils.setBudgetedAt
import com.ipritdev.catatananggaranbulanan.utils.testBudgets
import com.ipritdev.catatananggaranbulanan.utils.testCategories
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.empty
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class BudgetAndCategoryDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var budgetAndCategoryDao: BudgetAndCategoryDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

        budgetAndCategoryDao = db.budgetAndCategoryDao()

        db.categoryDao().insertCategories(testCategories)
        db.budgetDao().insertBudgets(testBudgets)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_getListBudgetAndCategory() {
        val budgetAndCategory = getValue(budgetAndCategoryDao.getListBudgetAndCategory())

        assertThat(budgetAndCategory, notNullValue())
        assertThat(budgetAndCategory.size, equalTo(4))
        assertThat(budgetAndCategory[0].category.id, equalTo(1))
        assertThat(budgetAndCategory[0].category.name, equalTo("Makan"))
        assertThat(budgetAndCategory[0].category.icon, equalTo("ic_eat"))
        assertThat(budgetAndCategory[0].budget.id, equalTo(1))
        assertThat(budgetAndCategory[0].budget.categoryId, equalTo(1))
        assertThat(budgetAndCategory[0].budget.amount, equalTo(600000))
        assertThat(budgetAndCategory[0].budget.budgetedAt, equalTo(Calendar.getInstance().apply {
            setBudgetedAt(2024, Calendar.JANUARY)
        }))
    }

    @Test
    fun test_getListBudgetAndCategoryByBudgetedAt() {
        val budgetAndCategory =
            getValue(budgetAndCategoryDao.getListBudgetAndCategory(Calendar.getInstance().apply {
            setBudgetedAt(2024, Calendar.JANUARY)
        }.timeInMillis))

        assertThat(budgetAndCategory, not(empty()))
        assertThat(budgetAndCategory.size, equalTo(4))
    }
}