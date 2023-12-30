package com.ipritdev.catatananggaranbulanan.expense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ipritdev.catatananggaranbulanan.databinding.FragmentExpenseBinding

class ExpenseFragment : Fragment() {

    private var _binding: FragmentExpenseBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExpenseBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}