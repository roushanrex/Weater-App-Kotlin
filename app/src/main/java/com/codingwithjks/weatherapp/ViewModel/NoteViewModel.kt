package com.codingwithjks.notepad.ui.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.Repository.WeatherRepository


class NoteViewModel : ViewModel(){



    fun getCardsData(context: Context):LiveData<List<Favorite>>?
    {
        return WeatherRepository.getCardData(context)
    }


}