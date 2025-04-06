package com.virent.gweather.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test
    fun `Long(Epoch Seconds) as DateTime String`() {
        val fetchTime = 1743827949L
        val offset = 28800

        assertEquals("Saturday, 05-Apr-2025 12:39", fetchTime.asDateTimeString(offset))
    }

    @Test
    fun `Long(Epoch Seconds) as Time String`() {
        val sunrise = 1743803336L
        val sunset = 1743847698L
        val offset = 28800

        assertEquals("05:48", sunrise.asTimeString(offset))
        assertEquals("18:08", sunset.asTimeString(offset))
    }

    @Test
    fun `Long(Epoch Seconds) as Hour`() {
        val fetchTime = 1743827949L
        val offset = 28800

        assertEquals(12, fetchTime.dateTimeHour(offset))
    }

    @Test
    fun `String Uppercase First`() {
        val string = "overcast clouds"
        assertEquals("Overcast clouds", string.upperCaseFirst())
    }

    @Test
    fun `Check Valid Password`() {
        val password1 = "Abc12345"
        val password2 = "a12345"
        val password3 = "Abc12345!"

        assertEquals(true, password1.isValidPassword())
        assertEquals(false, password2.isValidPassword())
        assertEquals(true, password3.isValidPassword())
    }

    @Test
    @Throws
    fun `Extract Username`() {
        val email1 = "abc@d.com"
        val email2 = "efg.com"
        val email3 = "virent@gmail.com"

        assertEquals("abc", email1.extractUsername())
        assertEquals("", email2.extractUsername())
        assertEquals("virent", email3.extractUsername())
    }
}