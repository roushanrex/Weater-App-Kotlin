package com.codingwithjks.weatherapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codingwithjks.weatherapp.Model.Favorite
import com.codingwithjks.weatherapp.Dao.FavoriteDao

@Database(entities = [Favorite::class],version = 3,exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase()
{
    abstract fun getDao():FavoriteDao

    companion object{
        private const val DATABASE_NAME="NoteDatabase"

        @Volatile
        var instance:FavoriteDatabase?=null

        fun getInstance(context: Context):FavoriteDatabase?
        {
            if(instance == null)
            {
                synchronized(FavoriteDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,FavoriteDatabase::class.java,
                            DATABASE_NAME).build()
                    }
                }
            }

            return instance
        }
    }
}