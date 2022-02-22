package com.ciandt.breweryees.api

import com.ciandt.breweryees.Model.BreweriesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface BreweriesService {

    @GET("/breweries/topTen")
    suspend fun breweriesTopTen():List<BreweriesModel>

    @GET("/breweries")
    suspend fun breweriesInitial(@Query(value = "by_city", encoded = true) by_city :String):List<BreweriesModel>
}