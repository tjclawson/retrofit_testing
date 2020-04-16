package com.tjclawson.retrofit_testing.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tjclawson.retrofit_testing.data.CurrentWeatherResponse
import com.tjclawson.retrofit_testing.network.WeatherstackApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private var weatherstackApiService = WeatherstackApiService()
    val currentWeather = MutableLiveData<CurrentWeatherResponse>()

    fun getWeather(location: String) {
        GlobalScope.launch {
            currentWeather.postValue(weatherstackApiService.getCurrentWeather(location))
        }
    }
}
