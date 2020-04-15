package com.tjclawson.retrofit_testing.data

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    val temperature: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    val precip: Double,
    @SerializedName("cloudcover")
    val cloudCover: Double,
    @SerializedName("uv_index")
    val uvIndex: Double
)