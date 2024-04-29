package ir.cafebazaar.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import ir.cafebazaar.database.db.MovieDatabase
import ir.cafebazaar.database.db.RemoteKeysDao
import ir.cafebazaar.database.model.RemoteKeys
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RemoteKeysDaoTest {

    private lateinit var dao: RemoteKeysDao
    private lateinit var db: MovieDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.remoteKeysDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertRemoteKeys_andVerify() = runTest {
        val movieId = 1L
        val remoteKeys = RemoteKeys(movieId, 10, 20)
        dao.insertRemoteKeys(listOf(remoteKeys))

        val retrievedKeys = dao.remoteKeysByMovieId(movieId)
        assertNotNull(retrievedKeys)
        assertEquals(remoteKeys, retrievedKeys)
    }

    @Test
    fun remoteKeysByMovieId_notFound() = runTest {
        val movieId = 1L
        val retrievedKeys = dao.remoteKeysByMovieId(movieId)
        assertNull(retrievedKeys)
    }

    @Test
    fun clearRemoteKeys_andVerifyEmpty() = runTest {
        val movieId = 1L
        val remoteKeys = RemoteKeys(movieId, 10, 20)
        dao.insertRemoteKeys(listOf(remoteKeys))
        dao.clearRemoteKeys()

        val retrievedKeys = dao.remoteKeysByMovieId(movieId)
        assertNull(retrievedKeys)
    }
}
