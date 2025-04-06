package com.virent.gweather.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExtensionsTest {

    @Test
    fun checkValidEmail() {
        val email1 = "abc@d.com"
        val email2 = "efg.com"
        val email3 = "virent@gmail.com"

        TestCase.assertEquals(true, email1.isValidEmail())
        TestCase.assertEquals(false, email2.isValidEmail())
        TestCase.assertEquals(true, email3.isValidEmail())
    }

}