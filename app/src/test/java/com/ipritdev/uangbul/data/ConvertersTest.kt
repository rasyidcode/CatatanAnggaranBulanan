package com.ipritdev.uangbul.data

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.Calendar

class ConvertersTest {

    private val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, 1996)
        set(Calendar.MONTH, Calendar.OCTOBER)
        set(Calendar.DATE, 7)
        set(Calendar.HOUR_OF_DAY, 5)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    @Test
    fun calendarToTimeMillis() {
        assertThat(calendar.timeInMillis, equalTo(Converters().calendarToTimeMillis(calendar)))
    }

    @Test
    fun timeMillisToCalendar() {
        assertThat(Converters().timeMillisToCalendar(calendar.timeInMillis), equalTo(calendar))
    }

}