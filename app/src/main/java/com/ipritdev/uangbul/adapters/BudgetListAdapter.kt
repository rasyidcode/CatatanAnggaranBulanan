package com.ipritdev.uangbul.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ipritdev.uangbul.R
import com.ipritdev.uangbul.data.Budget

class BudgetListAdapter(
    private val items: List<Budget>
) : RecyclerView.Adapter<BudgetListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_budget,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.budgetTitleView.text = items[position].categoryId.toString()
        holder.budgetAmountView.text = items[position].amount.toString()
        holder.itemView.tag = items[position]
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val budgetTitleView: TextView
        val budgetAmountView: TextView

        init {
            budgetTitleView = view.findViewById(R.id.budget_title)
            budgetAmountView = view.findViewById(R.id.budget_amount)
        }
    }
}