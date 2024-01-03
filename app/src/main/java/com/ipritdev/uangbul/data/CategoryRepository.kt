package com.ipritdev.uangbul.data

import com.ipritdev.uangbul.utils.runOnIoThread

class CategoryRepository private constructor(
    private val categoryDao: CategoryDao
) {

    fun getCategories() = categoryDao.getCategories()

    fun createCategory(name: String, icon: String) {
        runOnIoThread {
            val category = Category(name = name, icon = icon)
            categoryDao.insertCategory(category)
        }
    }

    companion object {
        @Volatile
        private var instance: CategoryRepository? = null

        fun getInstance(categoryDao: CategoryDao) = instance ?: synchronized(this) {
            instance ?: CategoryRepository(categoryDao).also { instance = it }
        }
    }

}