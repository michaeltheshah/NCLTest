package com.example.ncltest.network.ship

import com.example.ncltest.models.ShipResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ShipService {
    @GET("/cms-service/cruise-ships/{name}")
    suspend fun fetchShipInfo(@Path("name") name: String): Response<ShipResponse>
}