package com.codingwithjks.weatherapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "Favorite")
data class Favorite(
    var description:String,
    var name:String,
    var degree:String,
    var temp: String)

{
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
 }

