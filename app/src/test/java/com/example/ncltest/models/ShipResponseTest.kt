package com.example.ncltest.models

import junit.framework.TestCase
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.BufferedReader
import java.lang.Exception
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class ShipResponseTest: TestCase() {
    private var shipResponse: ShipResponse? = null

    private val json = Json { ignoreUnknownKeys = true }

    @Before
    public override fun setUp() {
        super.setUp()
        shipResponse = try {
            val stream = javaClass.getResourceAsStream("/ship_response.json") ?: throw IllegalStateException("File does not exist.")
            val reader = stream.bufferedReader().use(BufferedReader::readText)
            json.decodeFromString<ShipResponse>(reader)
        } catch (e: Exception) {
            null
        }
    }

    @Test
    fun shouldFormateShipName() {
        val correctShipName = "Ship Name: Norwegian Escape"
        assertEquals(shipResponse?.formattedShipName, correctShipName)
    }

    @Test
    fun shouldFormatPaxCapacity() {
        val correctPaxCapacity = "Passenger Capacity: 4,266 (double occupancy)"
        assertEquals(shipResponse?.formattedPaxCapacity, correctPaxCapacity)
    }

    @Test
    fun shouldFormatCrew() {
        val correctCrewCapacity = "Crew: 1,733"
        assertEquals(shipResponse?.formattedCrew, correctCrewCapacity)
    }

    @Test
    fun shouldFormatInauguralDate() {
        val correctInauguralDate = "Inaugural Date: 2015"
        assertEquals(shipResponse?.formattedInauguralDate, correctInauguralDate)
    }
}