package ir.cafebazaar.database

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import ir.cafebazaar.database.db.MovieDao
import ir.cafebazaar.database.db.MovieDatabase
import ir.cafebazaar.database.model.MovieLocalModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var dao: MovieDao
    private lateinit var db: MovieDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.movieDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getMovies_emptyDatabase() = runTest {
        val movies = dao.getMovies().first()
        assertTrue(movies.isEmpty())
    }

    @Test
    fun getMovies_withData() = runTest {
        val movies = listOf(
            MovieLocalModel(uuid = 1, id = 123, title = "Movie 1", cover = "cover.jpg"),
            MovieLocalModel(uuid = 2, id = 456, title = "Movie 2", cover = "cover2.jpg")
        )
        dao.insertMovies(movies)

        val retrievedMovies = dao.getMovies().first()
        assertEquals(movies, retrievedMovies)
        assertEquals(1, retrievedMovies[0].uuid)
        assertEquals(2, retrievedMovies[1].uuid)
    }

    @Test
    fun insertMovies_andVerify() = runTest {
        val movies = listOf(
            MovieLocalModel(uuid = 1, id = 123, title = "Movie 1", cover = "cover.jpg"),
            MovieLocalModel(uuid = 2, id = 456, title = "Movie 2", cover = "cover2.jpg")
        )
        dao.insertMovies(movies)

        val retrievedMovies = dao.getMovies().first()
        assertEquals(movies, retrievedMovies)
        assertEquals(1, retrievedMovies[0].uuid)
        assertEquals(2, retrievedMovies[1].uuid)
    }

    @Test
    fun clearMovies_andVerifyEmpty() = runTest {
        val movies = listOf(
            MovieLocalModel(id = 123, title = "Movie 1", cover = "cover.jpg"),
            MovieLocalModel(id = 456, title = "Movie 2", cover = "cover2.jpg")
        )
        dao.insertMovies(movies)
        dao.clearMovies()

        val retrievedMovies = dao.getMovies().first()
        assertTrue(retrievedMovies.isEmpty())
    }

    @Test
    fun pagingSource_emptyDatabase() = runTest {
        val pagingSource = dao.pagingSource()
        val loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertTrue(loadResult is PagingSource.LoadResult.Page)
        loadResult as PagingSource.LoadResult.Page
        assertEquals(loadResult.data.size, 0)
        assertEquals(loadResult.nextKey, null)
        assertEquals(loadResult.prevKey, null)
    }

    @Test
    fun pagingSource_withData() = runTest {
        val movies = (1..50).map {
            MovieLocalModel(id = it.toLong(), title = "Movie $it", cover = "cover$it.jpg")
        }
        dao.insertMovies(movies)

        val pagingSource = dao.pagingSource()
        val loadResult = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )
        )

        assertTrue(loadResult is PagingSource.LoadResult.Page)
        loadResult as PagingSource.LoadResult.Page
        assertEquals(loadResult.data.size, 10)
        assertEquals(loadResult.data[0].title, "Movie 1")
    }
}
