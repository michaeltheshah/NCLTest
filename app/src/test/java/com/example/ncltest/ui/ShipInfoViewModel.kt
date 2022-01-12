package com.example.ncltest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.ncltest.models.ShipResponse
import com.example.ncltest.network.ship.ShipManager
import com.example.ncltest.util.state.AwaitResult
import com.example.ncltest.util.state.State
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Response
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.BufferedReader
import java.lang.IllegalStateException
import java.util.*

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RestaurantListViewModelTest : TestCase() {
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var observer: Observer<in State<ShipResponse>>

    @Mock
    private lateinit var response: Response

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var shipManager: ShipManager

    @Mock
    private lateinit var viewModel: ShipInfoViewModel
    private val json = Json { ignoreUnknownKeys = true }
    private var successfulResponse: ShipResponse? = null

    @Before
    public override fun setUp() {
        super.setUp()
        Dispatchers.setMain(testDispatcher)
        viewModel = ShipInfoViewModel(shipManager)
        viewModel.shipLiveData.observeForever(observer)

        successfulResponse = try {
            val stream = javaClass.getResourceAsStream("/ship_response.json") ?: throw IllegalStateException("File does not exist.")
            val reader = stream.bufferedReader().use(BufferedReader::readText)
            json.decodeFromString<ShipResponse>(reader)
        } catch (e: Exception) {
            null
        }
    }

    @Test
    fun shouldParseJsonAsResponse() {
        val successfulResponse = successfulResponse
        if (successfulResponse == null) {
            fail("successResponse is null")
            return
        }

        assertEquals(successfulResponse.shipName, "Norwegian Escape")
        assertEquals(successfulResponse.shipFacts?.inauguralDate, "2015")
        assertEquals(successfulResponse.shipFacts?.crew, "1,733")
    }

    @Test
    fun shouldEmitSuccessState() = runBlocking {
        val successfulResponse = successfulResponse
        if (successfulResponse == null) {
            fail("successResponse is null")
            return@runBlocking
        }

        `when`(shipManager.fetchShipInfo("ESCAPE")).thenReturn(AwaitResult.Ok(successfulResponse, response))

        viewModel.fetchShipInfo("ESCAPE")
        verify(observer).onChanged(State.Success(successfulResponse))
        viewModel.shipLiveData.removeObserver(observer)
    }

    @Test
    fun shouldEmitErrorState() = runBlocking {
        val exception = Exception()
        `when`(shipManager.fetchShipInfo("ESCAPE")).thenReturn(AwaitResult.Error(exception))

        viewModel.fetchShipInfo("ESCAPE")
        verify(observer).onChanged(State.Error(exception))
        viewModel.shipLiveData.removeObserver(observer)
    }

    @After
    public override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}