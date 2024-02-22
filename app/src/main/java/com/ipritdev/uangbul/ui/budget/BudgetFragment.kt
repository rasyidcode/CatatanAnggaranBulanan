package com.ipritdev.uangbul.ui.budget

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ipritdev.uangbul.BudgetApplication
import com.ipritdev.uangbul.databinding.FragmentBudgetBinding
import java.util.Calendar

class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null

    private val binding get() = _binding!!

    private val budgetViewModel by viewModels<BudgetViewModel> {
        BudgetViewModel.BudgetViewModelFactory(
            (activity?.application as BudgetApplication).budgetRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = budgetViewModel
            lifecycleOwner = viewLifecycleOwner
            onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, _ ->
                budgetViewModel.setSelectedMonth(Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                })
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = "BudgetFragment"
    }

}