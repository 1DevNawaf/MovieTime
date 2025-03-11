package com.example.movietime.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun toTorrentList(torrent: List<MovieEntity.Torrent>): String{
        return Gson().toJson(torrent)
    }
    @TypeConverter
    fun fromTorrentList(torrent: String): List<MovieEntity.Torrent>{
        val listType = object : TypeToken<List<MovieEntity.Torrent>>() {}.type
        return Gson().fromJson(torrent, listType)
    }

    @TypeConverter
    fun fromGenreList(genres: List<String>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenreList(genreString: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(genreString, listType)
    }
}