package com.tjclawson.retrofit_testing.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.tjclawson.retrofit_testing.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.weather_fragment.*

class WeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherFragment()
    }

    private lateinit var viewModel: WeatherViewModel
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        initUi()
        initButtonListener()
    }

    private fun initUi() {
        viewModel.currentWeather.observe(viewLifecycleOwner, Observer {
            if (it != null)
                text_view_coroutine.text = it.currentWeather.toString()
        })
    }

    private fun initButtonListener() {
        button_get_weather_coroutine.setOnClickListener {
            if (et_location.text.toString().isNotEmpty())
                viewModel.getWeather(et_location.text.toString())
            else
                Toast.makeText(activity, "Please Enter Location", Toast.LENGTH_LONG).show()
        }

        button_get_weather_rx.setOnClickListener {
            if (et_location.text.toString().isNotEmpty())
                disposable = viewModel.getWeatherRxJava(et_location.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { data -> text_view_rx.text = data.currentWeather.toString()}
            else
                Toast.makeText(activity, "Please Enter Location", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
