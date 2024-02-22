package com.ipritdev.uangbul.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ipritdev.uangbul.utils.DATABASE_NAME
import kotlin.concurrent.Volatile

@Database(
    entities = [Category::class, Budget::class, Expense::class, BudgetTest::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun expenseDao(): ExpenseDao

    abstract fun budgetDao(): BudgetDao

    abstract fun budgetAndCategoryDao(): BudgetAndCategoryDao

    abstract fun budgetTestDao(): BudgetTestDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

}