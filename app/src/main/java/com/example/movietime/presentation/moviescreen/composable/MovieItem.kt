package com.example.movietime.presentation.moviescreen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.movietime.domain.Movie
import kotlin.math.roundToInt

@Composable
fun MovieItem(modifier: Modifier = Modifier,movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // Movie Image
            Image(
                painter = rememberImagePainter(data = movie.large_cover_image ?: movie.background_image),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Title in bold along with the year
            Text(
                text = "${movie.title} (${movie.year})",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            // Description (uses summary if available, otherwise description_full)
            Text(
                text = movie.description_full?: movie.language?: "Hello there",
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            // Genres in grey
            movie.genres?.takeIf { it.isNotEmpty() }?.let { genres ->
                Text(
                    text = genres.joinToString(", "),
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            // Rating: convert movie.rating (out of 10) to a 5-star scale
            val filledStars = (movie.rating!!.roundToInt())/2
            val emptyStars = 5 - filledStars

            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                repeat(filledStars) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700) // gold color
                    )
                }
                repeat(emptyStars) {
                    CustomEmptyStar(modifier = Modifier.size(24.dp))
                }
            }
            // Runtime: Convert minutes to hours and minutes
            val hours = movie.runtime!! / 60
            val minutes = movie.runtime % 60
            Text(
                text = "Duration: ${if (hours > 0) "${hours}h " else ""}${minutes}m",
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}