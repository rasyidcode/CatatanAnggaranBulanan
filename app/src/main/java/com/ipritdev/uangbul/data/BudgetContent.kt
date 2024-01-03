package com.ipritdev.uangbul.data

import com.ipritdev.uangbul.utils.setBudgetedAt
import java.util.Calendar

object BudgetContent {

    /**
     * List of sample (dummy) items.
     */
    val ITEMS = mutableListOf<Budget>()

    val ITEM_MAP = mutableMapOf<String, Budget>()

    init {
        addItem(
            Budget(
                id = 1,
                categoryId = 1,
                amount = 120000,
                budgetedAt = Calendar.getInstance().apply {
                    setBudgetedAt(2024, Calendar.JANUARY)
                }
            )
        )
        addItem(
            Budget(
                id = 2,
                categoryId = 2,
                amount = 220000,
                budgetedAt = Calendar.getInstance().apply {
                    setBudgetedAt(2024, Calendar.JANUARY)
                }
            )
        )
        addItem(
            Budget(
                id = 3,
                categoryId = 3,
                amount = 320000,
                budgetedAt = Calendar.getInstance().apply {
                    setBudgetedAt(2024, Calendar.JANUARY)
                }
            )
        )
        addItem(
            Budget(
                id = 4,
                categoryId = 4,
                amount = 420000,
                budgetedAt = Calendar.getInstance().apply {
                    setBudgetedAt(2024, Calendar.JANUARY)
                }
            )
        )
    }

    private fun addItem(item: Budget) {
        ITEMS.add(item)
        ITEM_MAP[item.id.toString()] = item
    }

}