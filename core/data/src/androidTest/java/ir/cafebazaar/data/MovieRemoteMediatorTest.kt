package ir.cafebazaar.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.ext.junit.runners.AndroidJUnit4
import ir.cafebazaar.data.repository.MovieRemoteMediator
import ir.cafebazaar.database.datasource.MovieLocalDataSource
import ir.cafebazaar.database.model.MovieLocalModel
import ir.cafebazaar.database.model.RemoteKeys
import ir.cafebazaar.network.datasource.MovieRemoteDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalPagingApi::class)
@RunWith(AndroidJUnit4::class)
class MovieRemoteMediatorTest {

    @Mock
    lateinit var mockRemoteDataSource: MovieRemoteDataSource

    @Mock
    lateinit var mockLocalDataSource: MovieLocalDataSource

    @InjectMocks
    lateinit var mediator: MovieRemoteMediator

    private val pagingConfig = PagingConfig(pageSize = 20, enablePlaceholders = false)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun load_refresh_emptyRemoteKeys() = runTest {
        val state = PagingState<Int, MovieLocalModel>(
            listOf(),
            null,
            pagingConfig,
            0
        )

        Mockito.`when`(mockRemoteDataSource.getMovies(1)).thenReturn(listOf())

        val result = mediator.load(LoadType.REFRESH, state) as RemoteMediator.MediatorResult.Success
        assertEquals(true, result.endOfPaginationReached)
    }

    @Test
    fun load_refresh_withRemoteKeys() = runTest {
        val remoteKeys = RemoteKeys(123, prevKey = 1, nextKey = 3)
        mockLocalDataSource.saveRemoteKeys(listOf(remoteKeys))

        val movie = MovieLocalModel(uuid = 1, id = 123, title = "Movie 1", cover = "cover.jpg")
        mockLocalDataSource.saveMovies(listOf(movie))

        val state = PagingState(
            listOf(
                PagingSource.LoadResult.Page(
                    listOf(movie),
                    remoteKeys.prevKey,
                    remoteKeys.nextKey
                )
            ),
            null,
            pagingConfig,
            0
        )

        Mockito.`when`(mockRemoteDataSource.getMovies(1)).thenReturn(listOf())

        val result = mediator.load(LoadType.REFRESH, state) as RemoteMediator.MediatorResult.Success
        assertEquals(true, result.endOfPaginationReached)
    }

    @Test
    fun load_prepend_withRemoteKeys() = runTest {
        val remoteKeys = RemoteKeys(123, prevKey = 1, nextKey = 3)
        mockLocalDataSource.saveRemoteKeys(listOf(remoteKeys))

        val movie = MovieLocalModel(uuid = 1, id = 123, title = "Movie 1", cover = "cover.jpg")
        mockLocalDataSource.saveMovies(listOf(movie))

        val state = PagingState(
            listOf(
                PagingSource.LoadResult.Page(
                    listOf(movie),
                    remoteKeys.prevKey,
                    remoteKeys.nextKey
                )
            ),
            null,
            pagingConfig,
            1
        )

        Mockito.`when`(mockRemoteDataSource.getMovies(1)).thenReturn(listOf())
        Mockito
            .`when`(mockLocalDataSource.getRemoteKeysByMovieId(123))
            .thenReturn(remoteKeys)

        val result = mediator.load(LoadType.PREPEND, state) as RemoteMediator.MediatorResult.Success
        assertEquals(true, result.endOfPaginationReached)
    }

    @Test
    fun load_append_withRemoteKeys() = runTest {
        val remoteKeys = RemoteKeys(123, prevKey = 1, nextKey = 3)
        mockLocalDataSource.saveRemoteKeys(listOf(remoteKeys))

        val movie = MovieLocalModel(uuid = 1, id = 123, title = "Movie 1", cover = "cover.jpg")
        mockLocalDataSource.saveMovies(listOf(movie))

        val state = PagingState(
            listOf(
                PagingSource.LoadResult.Page(
                    listOf(movie),
                    remoteKeys.prevKey,
                    remoteKeys.nextKey
                )
            ),
            null,
            pagingConfig,
            1
        )

        Mockito.`when`(mockRemoteDataSource.getMovies(3)).thenReturn(listOf())
        Mockito
            .`when`(mockLocalDataSource.getRemoteKeysByMovieId(123))
            .thenReturn(remoteKeys)

        val result = mediator.load(LoadType.APPEND, state) as RemoteMediator.MediatorResult.Success
        assertEquals(true, result.endOfPaginationReached)
    }
}