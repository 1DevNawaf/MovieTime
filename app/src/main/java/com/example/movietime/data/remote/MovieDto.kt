package com.example.movietime.data.remote

data class MoviesPage(
    val meta: Meta?,
    val `data`: Data?,
    val status: String?,
    val status_message: String?
) {
    data class Meta(
        val api_version: Int?,
        val execution_time: String?,
        val server_time: Int?,
        val server_timezone: String?
    )

    data class Data(
        val limit: Int?,
        val movie_count: Int?,
        val movies: List<MovieDto>?,
        val page_number: Int?
    ) {
        data class MovieDto(
            val id: Int?,
            val background_image: String?,
            val background_image_original: String?,
            val date_uploaded: String?,
            val date_uploaded_unix: Int?,
            val description_full: String?,
            val genres: List<String>?,
            val imdb_code: String?,
            val language: String?,
            val large_cover_image: String?,
            val medium_cover_image: String?,
            val mpa_rating: String?,
            val rating: Double?,
            val runtime: Int?,
            val slug: String?,
            val small_cover_image: String?,
            val state: String?,
            val summary: String?,
            val synopsis: String?,
            val title: String?,
            val title_english: String?,
            val title_long: String?,
            val torrents: List<Torrent>?,
            val url: String?,
            val year: Int?,
            val yt_trailer_code: String?
        ) {
            data class Torrent(
                val audio_channels: String?,
                val bit_depth: String?,
                val date_uploaded: String?,
                val date_uploaded_unix: Int?,
                val hash: String?,
                val is_repack: String?,
                val peers: Int?,
                val quality: String?,
                val seeds: Int?,
                val size: String?,
                val size_bytes: Long?,
                val type: String?,
                val url: String?,
                val video_codec: String?
            )
        }
    }
}