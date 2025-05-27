package app.mastani.cathashtad.features.favorites.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.ElevatedCard
import app.mastani.cathashtad.designSystem.NetworkImage
import app.mastani.cathashtad.designSystem.component.CatFriendlyBar
import app.mastani.cathashtad.designSystem.theme.Gray4
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.R

@Composable
fun FavoriteBreed(
    breed: CatBreedUiModel,
    onClick: (String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp),
        elevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable {
                    onClick(breed.id)
                },
            contentAlignment = Alignment.BottomStart
        ) {
            NetworkImage(
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Gray4),
                            startY = size.height / 1.5f,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                url = breed.imageUrl ?: "",
                placeholder = R.drawable.ic_cat,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopStart
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 12.dp),
                    text = breed.name,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CatFriendlyBar(
                        modifier = Modifier.padding(horizontal = 6.dp),
                        size = 18.dp,
                        rating = breed.affectionLevel ?: 0
                    )

                    VerticalDivider(
                        modifier = Modifier.height(18.dp),
                        color = Color.Gray
                    )

                    NetworkImage(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .size(24.dp, 18.dp),
                        url = "https://flagcdn.com/24x18/${breed.countryCode?.lowercase()}.png",
                        placeholder = R.drawable.ic_flag,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FavoriteBreedPreview() {
    FavoriteBreed(
        breed = CatBreedUiModel.PREVIEW,
        onClick = {}
    )
}