package com.virent.gweather.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArchiveEntryDaoTest {

    private lateinit var archiveDao: ArchiveEntryDao
    private lateinit var archiveDatabase: ArchiveDatabase

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

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        archiveDatabase = Room.inMemoryDatabaseBuilder(
            context,
            ArchiveDatabase::class.java
        ).allowMainThreadQueries().build()
        archiveDao = archiveDatabase.archiveDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        archiveDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertItemIntoDB() = runTest {
        archiveDao.add(ENTRY_1)
        val userArchive = archiveDao.getUserArchive(ENTRY_1.user).first()
        TestCase.assertEquals(ENTRY_1, userArchive[0])
    }

    @Test
    @Throws
    fun daoGetUserArchive_returnsItemFromDB() = runTest {
        archiveDao.add(ENTRY_1)
        archiveDao.add(ENTRY_2)
        archiveDao.add(ENTRY_3)

        val userArchive = archiveDao.getUserArchive(ENTRY_2.user).first()
        TestCase.assertEquals(1, userArchive.size)
        TestCase.assertEquals(ENTRY_2, userArchive[0])
    }

    @Test
    @Throws
    fun daoGetUserArchive_returnsItemsFromDB() = runTest {
        archiveDao.add(ENTRY_1)
        archiveDao.add(ENTRY_2)
        archiveDao.add(ENTRY_3)

        val userArchive = archiveDao.getUserArchive(ENTRY_1.user).first()
        TestCase.assertEquals(userArchive.size, 2)
        TestCase.assertEquals(ENTRY_3, userArchive[0])
        TestCase.assertEquals(ENTRY_1, userArchive[1])
    }

}