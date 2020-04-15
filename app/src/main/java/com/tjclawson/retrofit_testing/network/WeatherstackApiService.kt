package com.tjclawson.retrofit_testing.network

import com.tjclawson.retrofit_testing.BuildConfig
import com.tjclawson.retrofit_testing.data.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherstackApiService {

    companion object {
        const val BASE_URL = "http://api.weatherstack.com/"
        const val API_KEY = BuildConfig.weatherstack_api_key

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()

                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor it.proceed(request)
            })
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherstackApiService::class.java)
    }

    @GET("current")
    fun getCurrentWeather(@Query("query") location: String): Deferred<CurrentWeatherResponse>
}