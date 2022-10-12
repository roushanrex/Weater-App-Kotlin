package com.codingwithjks.weatherapp.Repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.Database.FavoriteDatabase
import com.codingwithjks.weatherapp.Model.City
import com.codingwithjks.weatherapp.Network.ApiServiceImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(city:String):Flow<City> = flow {
        val response= apiServiceImp.getCity(city,"a45bda185288cef6b03035dd614f61b1")
        Log.d("testapii",""  + response.toString());

        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()

    companion object {
        private var noteDatabase: FavoriteDatabase? = null

        private fun initialiseDB(context: Context): FavoriteDatabase? {
            return FavoriteDatabase.getInstance(context)
        }

        fun insert(context: Context, note: Favorite) {
            noteDatabase = initialiseDB(context)

            CoroutineScope(IO).launch {
                noteDatabase?.getDao()?.insert(note)
            }
        }

        fun getCardData(context: Context): LiveData<List<Favorite>>?
        {
            noteDatabase= initialiseDB(context)
            return noteDatabase?.getDao()?.getCardsData()
        }

    }




}