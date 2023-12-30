package com.ipritdev.catatananggaranbulanan.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BudgetTestDao {

    @Query("SELECT * FROM BudgetTest")
    fun getAllBudget(): List<BudgetTest>

    @Query("SELECT id, name, created_at, date(created_at, 'unixepoch') as createdAtString FROM BudgetTest")
    fun getBudget(): List<BudgetTest>

    @Query("""
            SELECT 
                id, name, created_at, date(created_at, 'unixepoch') AS createdAtString 
            FROM BudgetTest 
            WHERE 
                date(created_at, 'unixepoch') = date(:time, 'unixepoch')""")
    fun getBudget2(time: Long): List<BudgetTest>

    @Query("SELECT * FROM BudgetTest WHERE created_at = :timestamp")
    fun getBudgetWhereTimestamp(timestamp: Long): List<BudgetTest>

    /**
     * Return budget by timestamp
     *
     * @return budget
     */
    @Query("SELECT * FROM BudgetTest WHERE created_at = :timestamp")
    fun getBudget(timestamp: Long): BudgetTest

    /**
     * Return budget by year and month
     *
     * @return budget
     */
    @Query("SELECT * FROM BudgetTest WHERE created_at = :year AND created_at = :month")
    fun getBudget(year: Int, month: Int): BudgetTest

    @Insert
    suspend fun insertBudgets(budgets: List<BudgetTest>)

}