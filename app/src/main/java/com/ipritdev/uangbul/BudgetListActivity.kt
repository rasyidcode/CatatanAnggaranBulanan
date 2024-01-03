package com.ipritdev.uangbul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ipritdev.uangbul.adapters.BudgetListAdapter
import com.ipritdev.uangbul.data.BudgetContent
import com.ipritdev.uangbul.databinding.ActivityBudgetListBinding

class BudgetListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBudgetListBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_budget_list
        )

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        binding.fab.setOnClickListener {
            Snackbar.make(it, "TODO: Add new budget to list", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        setupRecyclerView(binding.budgetListFrame.budgetList)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = BudgetListAdapter(BudgetContent.ITEMS)
    }

}