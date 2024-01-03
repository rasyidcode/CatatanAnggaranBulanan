package com.ipritdev.uangbul

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date
import java.util.logging.Level
import java.util.logging.Logger

class CodeTest {
    @Test
    fun test_javaTimePackage() {
        // get current epochTime seconds
        println("Instant.now().epochSecond: " + Instant.now().epochSecond)
        // get current epochTime milliseconds
        println("Instant.now().toEpochMilli(): " + Instant.now().toEpochMilli())
        // convert epochTime to LocalDate
        println(
            "Instant.now(): " + Instant.ofEpochMilli(Instant.now().toEpochMilli())
                .atZone(ZoneId.systemDefault()).toLocalDate()
        )
        // get current date
        println("LocalDate.now(): " + LocalDate.now())
        // convert string to current date
        println("LocalDate: " + LocalDate.parse("2023-12-25"))
        // LocalDateTime now
        println("LocalDateTime.now(): " + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
        // convert string to LocalDateTime
        println(
            "LocalDateTime: " + LocalDateTime.parse("2023-12-25T04:18:32")
                .toEpochSecond(ZoneOffset.UTC)
        )
    }

    @Test
    fun test_javaUtil_Calendar() {
        val calendar = Calendar.getInstance()
        // get current epochTime in milliseconds
        println("epochTime_milliseconds: " + calendar.timeInMillis)
        // epochTime in milliseconds to Date
        println("dateFromEpochMillis: " + Date(calendar.timeInMillis))
        // epochTime in milliseconds to DateTime
        // System currentTimeMillis
        println("currentTimeMillis: " + System.currentTimeMillis())
        println("dateFromEpochMillis: " + Date(System.currentTimeMillis()))
        println("Calendar_from_timeInMillis: " + Calendar.getInstance().apply {
            timeInMillis = calendar.timeInMillis
        })
    }

    @Test
    fun test_getCurrentEpochTime() {
        println("epochtime: " + Instant.EPOCH.epochSecond)
        println("currentEpochTime: " + Instant.now().epochSecond)
        println("localDate_EPOCH: " + LocalDate.EPOCH)
        println("dateFromEpochSecond: " + Date(Instant.now().epochSecond * 1000))

        // LocalDate
        println("LocalDate_now(): " + LocalDate.now())
        println("LocalDate_EPOCH: " + LocalDate.EPOCH)

        // LocalTime
        println("LocalTime_now(): " + LocalTime.now())

        // LocalDateTime
        println("LocalDateTime_now(): " + LocalDateTime.now())
    }

    @Test
    fun test_justLogger() {
        Logger.getGlobal().log(Level.INFO, "test logging info")
        Logger.getGlobal().log(Level.FINE, "test logging fine")
        Logger.getGlobal().log(Level.FINER, "test logging finer")
        Logger.getGlobal().log(Level.WARNING, "test logging warning")
        Logger.getGlobal().log(Level.SEVERE, "test logging severe")
        Logger.getGlobal().log(Level.ALL, "test logging all")
    }

    @Test
    fun test_time() = runBlocking {
        println("time: " + Calendar.getInstance().time)
        println("timetime: " + Calendar.getInstance().time.time)
        val time = Calendar.getInstance().timeInMillis / 1000
        println("time: $time")

        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val cal2 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val cal3 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2014)
        }
        delay(2000L)
        val cal4 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2014)
        }
        assertThat(cal.timeInMillis, equalTo(cal2.timeInMillis))

        println("cal3: ${cal3.timeInMillis}, time: ${cal3.time}")
        println("cal4: ${cal4.timeInMillis}, time: ${cal4.time}")
        assertThat(cal3.timeInMillis, equalTo(cal4.timeInMillis))
    }

    @Test
    fun test_calendar() = runBlocking {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        delay(2000L)
        val calendar2 = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.JANUARY)
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        println("calendar: ${calendar.timeInMillis}")
        println("calendar2: ${calendar2.timeInMillis}")

        assertThat(calendar.timeInMillis, equalTo(calendar2.timeInMillis))
    }
}