package app.mastani.cathashtad.features.home.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.ElevatedCard
import app.mastani.cathashtad.designSystem.NetworkImage
import app.mastani.cathashtad.designSystem.component.CatFriendlyBar
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.R

@Composable
fun CatBreed(
    breed: CatBreedUiModel,
    onClick: (String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(breed.id)
                }
        ) {
            NetworkImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
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
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = breed.name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 4.dp),
                        text = breed.temperament
                            ?.ifBlank { breed.altNames }
                            ?.ifBlank { breed.description }
                            ?: "",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }

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
                            .size(22.dp, 16.dp),
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
fun CatBreedPreview() {
    CatBreed(
        breed = CatBreedUiModel.PREVIEW,
        onClick = {}
    )
}