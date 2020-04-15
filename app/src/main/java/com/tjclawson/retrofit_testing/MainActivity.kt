package com.tjclawson.retrofit_testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjclawson.retrofit_testing.ui.WeatherFragment

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, WeatherFragment())
            .commit()
    }
}
