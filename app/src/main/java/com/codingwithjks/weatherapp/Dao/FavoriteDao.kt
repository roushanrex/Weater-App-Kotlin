package com.codingwithjks.weatherapp.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codingwithjks.weatherapp.Model.Favorite

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(note: Favorite)

    @Query("SELECT * FROM Favorite ORDER BY id DESC")
    fun getCardsData():LiveData<List<Favorite>>

}