package com.ipritdev.uangbul.ui.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ipritdev.uangbul.databinding.DialogMonthYearPickerBinding
import java.util.Calendar

class MonthYearPickerDialog(
    private val calendar: Calendar
) : DialogFragment() {

    private lateinit var binding: DialogMonthYearPickerBinding

    private var onDateSetListener: OnDateSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogMonthYearPickerBinding.inflate(layoutInflater)

        binding.monthPicker.apply {
            minValue = Calendar.JANUARY
            maxValue = Calendar.DECEMBER
            value = calendar.get(Calendar.MONTH)
            displayedValues = arrayOf(
                "Jan", "Feb", "Mar", "Apr", "May",
                "Jun", "Jul", "Aug", "Sep", "Oct",
                "Nov", "Dec"
            )
        }
        binding.yearPicker.apply {
            minValue = 1970
            maxValue = 2999
            value = calendar.get(Calendar.YEAR)
        }

        return AlertDialog.Builder(requireContext())
            .setTitle("Please select month and year")
            .setView(binding.root)
            .setPositiveButton("Ok") { _, _ ->
                onDateSetListener?.onDateSet(
                    null,
                    binding.yearPicker.value,
                    binding.monthPicker.value,
                    1
                )
            }
            .setNegativeButton("Cancel") { _, _ -> dialog?.cancel() }
            .create()
    }

    fun onDateSetListener(listener: OnDateSetListener?) {
        onDateSetListener = listener
    }

    companion object {
        val TAG = "MonthYearPickerDialog"
    }

}