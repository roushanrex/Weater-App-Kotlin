package com.codingwithjks.weatherapp.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.Model.City
import com.codingwithjks.weatherapp.Model.Main
import com.codingwithjks.weatherapp.Model.Weather
import com.codingwithjks.weatherapp.Model.Wind
import com.codingwithjks.weatherapp.Repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _weatherResponse: MutableStateFlow<City> = MutableStateFlow(
        City(
            listOf(Weather()),
            Main(),
            Wind()
        )
    )
    val weatherResponse: StateFlow<City> = _weatherResponse
    private val searchChannel = MutableSharedFlow<String>(1)


    fun insert(context:Context,note:Favorite)
    {
        WeatherRepository.insert(context,note)
    }


    fun setSearchQuery(search: String) {
        searchChannel.tryEmit(search)
    }

    init {
        getCityData()
    }


    fun getCardsData(context: Context):LiveData<List<Favorite>>?
    {
        return WeatherRepository.getCardData(context)
    }



    private fun getCityData() {
        viewModelScope.launch {
            searchChannel
                .flatMapLatest { search ->
                    weatherRepository.getCityData(search)
                }.catch { e ->
                    Log.d("mainCheck", "${e.message}")
                }.collect { response ->
                    _weatherResponse.value = response
                }
        }
    }


}