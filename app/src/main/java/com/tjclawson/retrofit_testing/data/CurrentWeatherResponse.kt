package com.tjclawson.retrofit_testing.data

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val currentWeather: CurrentWeather
)