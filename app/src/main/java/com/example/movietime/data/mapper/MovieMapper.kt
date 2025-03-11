package com.example.movietime.data.mapper

import com.example.movietime.data.local.MovieEntity
import com.example.movietime.data.remote.MovieDto
import com.example.movietime.domain.Movie

fun MovieDto.toMovieEntity() : MovieEntity {
    return MovieEntity(
        id = id,
        background_image = background_image,
        background_image_original = background_image_original,
        date_uploaded = date_uploaded,
        date_uploaded_unix = date_uploaded_unix,
        description_full = description_full,
        genres = genres,
        imdb_code = imdb_code,
        language = language,
        large_cover_image = large_cover_image,
        medium_cover_image = medium_cover_image,
        mpa_rating = mpa_rating,
        rating = rating,
        runtime = runtime,
        slug = slug,
        small_cover_image = small_cover_image,
        state = state,
        summary = summary,
        synopsis = synopsis,
        title = title,
        title_english = title_english,
        title_long = title_long,
        torrents = torrents.map {
            MovieEntity.Torrent(
                audio_channels = it.audio_channels,
                bit_depth = it.bit_depth,
                date_uploaded = it.date_uploaded,
                date_uploaded_unix = it.date_uploaded_unix,
                hash = it.hash,
                is_repack = it.is_repack,
                peers = it.peers,
                quality = it.quality,
                seeds = it.seeds,
                size = it.size,
                size_bytes = it.size_bytes,
                type = it.type,
                url = it.url,
                video_codec = it.video_codec
            )
        },
        url = url,
        year = year,
        yt_trailer_code = yt_trailer_code
    )
}


fun MovieEntity.toMovie() : Movie{
    return Movie(
        id = id,
        background_image = background_image,
        background_image_original = background_image_original,
        date_uploaded = date_uploaded,
        date_uploaded_unix = date_uploaded_unix,
        description_full = description_full,
        genres = genres,
        imdb_code = imdb_code,
        language = language,
        large_cover_image = large_cover_image,
        medium_cover_image = medium_cover_image,
        mpa_rating = mpa_rating,
        rating = rating,
        runtime = runtime,
        slug = slug,
        small_cover_image = small_cover_image,
        state = state,
        summary = summary,
        synopsis = synopsis,
        title = title,
        title_english = title_english,
        title_long = title_long,
        torrents = torrents.map {
            Movie.Torrent(
                audio_channels = it.audio_channels,
                bit_depth = it.bit_depth,
                date_uploaded = it.date_uploaded,
                date_uploaded_unix = it.date_uploaded_unix,
                hash = it.hash,
                is_repack = it.is_repack,
                peers = it.peers,
                quality = it.quality,
                seeds = it.seeds,
                size = it.size,
                size_bytes = it.size_bytes,
                type = it.type,
                url = it.url,
                video_codec = it.video_codec
            )
        },
        url = url,
        year = year,
        yt_trailer_code = yt_trailer_code
    )
}