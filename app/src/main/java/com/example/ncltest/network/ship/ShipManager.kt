package com.example.ncltest.network.ship

import com.example.ncltest.models.ShipResponse
import com.example.ncltest.util.extensions.awaitResult
import com.example.ncltest.util.state.AwaitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class ShipManager @Inject constructor(private val service: ShipService) {
    suspend fun fetchShipInfo(name: String): AwaitResult<ShipResponse> {
        return try {
            withContext(Dispatchers.IO) {
                service.fetchShipInfo(name).awaitResult()
            }
        } catch (e: Exception) {
            AwaitResult.Error(e)
        }
    }
}