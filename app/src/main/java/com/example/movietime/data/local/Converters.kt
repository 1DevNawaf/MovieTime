package com.example.movietime.data.local

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverters
    fun toTorrentList(torrent: List<MovieEntity.Torrent>): String{
        return Gson().toJson(torrent)
    }
    @TypeConverters
    fun fromTorrentList(torrent: String): List<MovieEntity.Torrent>{
        val listType = object : TypeToken<List<MovieEntity.Torrent>>() {}.type
        return Gson().fromJson(torrent, listType)
    }
}