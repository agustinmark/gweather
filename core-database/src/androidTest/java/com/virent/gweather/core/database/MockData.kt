package com.virent.gweather.core.database

object MockData {


    val ENTRY_1 = ArchiveEntry(
        id = 1,
        user = "abc@d.com",
        dateTime = 1743827760,
        offset = 28800,
        weather = "CLOUDS",
        description = "broken clouds",
        temp = 32.86,
        feelsLike = 39.86,
        tempMin = 31.14,
        tempMax = 33.0,
        cloudiness = 75,
        humidity = 62,
        windSpeed = 4.12,
        windDegree = 120,
        city = "Sambayanihan People's Village",
        countryCode = "PH",
        sunrise = 1743803336,
        sunset = 1743847698,
    )

    val ENTRY_2 = ArchiveEntry(
        id = 2,
        user = "efg@h.com",
        dateTime = 1743828405,
        offset = 28800,
        weather = "CLOUDS",
        description = "broken clouds",
        temp = 32.48,
        feelsLike = 38.55,
        tempMin = 31.62,
        tempMax = 32.92,
        cloudiness = 75,
        humidity = 61,
        windSpeed = 4.12,
        windDegree = 120,
        city = "Makati City",
        countryCode = "PH",
        sunrise = 1743803321,
        sunset = 1743847687
    )

    val ENTRY_3 = ArchiveEntry(
        id = 3,
        user = "abc@d.com",
        dateTime = 1743828856,
        offset = 28800,
        weather = "CLOUDS",
        description = "broken clouds",
        temp = 32.54,
        feelsLike = 39.05,
        tempMin = 30.51,
        tempMax = 32.97,
        cloudiness = 75,
        humidity = 62,
        windSpeed = 4.12,
        windDegree = 120,
        city = "City of Marikina",
        countryCode = "PH",
        sunrise = 1743803306,
        sunset = 1743847677,
    )
}