package com.ipritdev.catatananggaranbulanan.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ipritdev.catatananggaranbulanan.utils.getValue
import com.ipritdev.catatananggaranbulanan.utils.testCategories
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

@RunWith(AndroidJUnit4::class)
class CategoryDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var categoryDao: CategoryDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()

        categoryDao = db.categoryDao()
        categoryDao.insertCategories(testCategories)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_getCategories() {
        val categories = getValue(categoryDao.getCategories())

        assertThat(categories.size, equalTo(4))
        assertThat(categories.first().id, equalTo(1))
        assertThat(categories.first().name, equalTo("Makan"))
        assertThat(categories.first().icon, equalTo("ic_eat"))
    }

    @Test
    fun test_getCategoryById() {
        val category = getValue(categoryDao.getCategoryById(1))

        assertThat(category, notNullValue())
        assertThat(category?.id, equalTo(1))
        assertThat(category?.name, equalTo("Makan"))
        assertThat(category?.icon, equalTo("ic_eat"))
    }

    @Test
    fun test_insertNewCategory() = runBlocking {
        categoryDao.insertCategory(Category(name = "Galon", icon = "ic_galon"))

        val category = getValue(categoryDao.getCategoryById(5))

        assertThat(category, notNullValue())
        assertThat(category?.id, equalTo(5))
        assertThat(category?.name, equalTo("Galon"))
        assertThat(category?.icon, equalTo("ic_galon"))
    }


    @Test
    fun test_updateCategory() = runBlocking {
        categoryDao.updateCategory(
            Category(
                id = 4,
                name = "Motor",
                icon = "ic_bike"
            )
        )

        val category = getValue(categoryDao.getCategoryById(4))

        assertThat(category, notNullValue())
        assertThat(category?.id, equalTo(4))
        assertThat(category?.name, equalTo("Motor"))
        assertThat(category?.icon, equalTo("ic_bike"))
    }


    @Test
    fun test_deleteCategory() = runBlocking {
        categoryDao.deleteCategory(
            Category(
                id = 4,
                name = "Lain-lain",
                icon = "ic_others"
            )
        )

        val category = getValue(categoryDao.getCategoryById(4))

        assertThat(category, nullValue())
    }

    @Test
    fun test_deleteCategories() = runBlocking {
        categoryDao.clearCategories()

        val categories = getValue(categoryDao.getCategories())

        assertThat(categories, empty())
    }

}