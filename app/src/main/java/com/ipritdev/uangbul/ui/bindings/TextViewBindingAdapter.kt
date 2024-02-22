package com.ipritdev.uangbul.ui.bindings

import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import com.ipritdev.uangbul.ui.dialogs.MonthYearPickerDialog
import java.util.Calendar

private const val TAG = "TextViewBindingAdapter"

@BindingAdapter("onClickMonthYear", "onDateSetListener", "context")
fun bindTextViewMonthYear(
    textView: TextView,
    selectedMonth: Calendar,
    onDateSetListener: OnDateSetListener?,
    context: Context?
) {
    textView.setOnClickListener {
        MonthYearPickerDialog(selectedMonth).apply {
            onDateSetListener(onDateSetListener)
            context?.let {
                show((it as FragmentActivity).supportFragmentManager, "MonthYearPickerDialog")
            }
        }
    }
}