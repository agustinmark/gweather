package com.virent.gweather.core.network

import com.virent.gweather.core.network.model.Clouds
import com.virent.gweather.core.network.model.Coord
import com.virent.gweather.core.network.model.CurrentWeatherResponse
import com.virent.gweather.core.network.model.Main
import com.virent.gweather.core.network.model.Sys
import com.virent.gweather.core.network.model.Weather
import com.virent.gweather.core.network.model.Wind

object MockData {
    const val EMAIL = "abc@d.com"
    const val PASSWORD = "Abc12345!"
    const val WRONG_PASSWORD = "wrongpassword"

    const val SIGNIN_ERROR_MESSAGE = "Invalid password"
    const val SIGNUP_ERROR_MESSAGE = "Email already used"
    const val TEST_EXCEPTION_FAIL_MESSAGE = "Exception should have been thrown"
}