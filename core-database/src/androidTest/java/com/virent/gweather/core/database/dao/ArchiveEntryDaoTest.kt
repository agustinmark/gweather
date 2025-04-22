package com.virent.gweather.core.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.virent.gweather.core.database.ArchiveDatabase
import com.virent.gweather.core.database.MockData.ENTRY_1
import com.virent.gweather.core.database.MockData.ENTRY_2
import com.virent.gweather.core.database.MockData.ENTRY_3
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