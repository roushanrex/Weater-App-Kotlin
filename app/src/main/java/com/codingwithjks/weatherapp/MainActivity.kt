package com.codingwithjks.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.ViewModel.WeatherViewModel
import com.codingwithjks.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.math.roundToInt
import kotlin.properties.Delegates

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    private lateinit var getData:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initListener()
        lifecycleScope.launchWhenStarted {
            weatherViewModel.weatherResponse.collect { response ->

                Log.d("mainCheck",response.weather[0].description);


                binding.description.text = response.weather[0].description
//                binding.name.text = response.name
//                binding.degree.text = response.wind.deg.toString()
                binding.speed.text = response.wind.speed.toString()
                binding.temp.text =
                    (((response.main.temp - 273) * 100.0).roundToInt() / 100.0).toString()
                binding.humidity.text = response.main.humidity.toString()

            }
        }

        binding.favorites.setOnClickListener {

//            val intent = Intent(this, Fav_list_Activity::class.java)
//            startActivity(intent)

            getData= binding.description.text.toString()
            getData= binding.speed.text.toString()
            getData= binding.temp.text.toString()
            getData= binding.temp.text.toString()
            weatherViewModel.insert(applicationContext, Favorite(getData,getData,getData,getData))
            Toast.makeText(this, "Add Favorites Successfully", Toast.LENGTH_SHORT).show()

        }
        binding.listshow.setOnClickListener {

            val intent = Intent(this, Fav_list_Activity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Details List", Toast.LENGTH_SHORT).show()
        }


    }

    @ExperimentalCoroutinesApi
    private fun  initListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.setSearchQuery(it) }
                Log.d("mainCheck", "onQueryTextChange: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
}
