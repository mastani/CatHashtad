package app.mastani.cathashtad.features.search.screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mastani.cathashtad.designSystem.NetworkImage
import app.mastani.cathashtad.domain.repository.breed.model.CatBreedUiModel
import app.mastani.cathashtad.features.R

@Composable
fun SearchItem(
    breed: CatBreedUiModel,
    onNavigateToCatBreedDetail: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                onNavigateToCatBreedDetail(breed.id)
            },
        verticalAlignment = Alignment.Top
    ) {
        NetworkImage(
            modifier = Modifier
                .width(120.dp)
                .height(140.dp),
            url = breed.imageUrl ?: "",
            placeholder = R.drawable.ic_cat,
            contentScale = ContentScale.FillHeight
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = breed.name,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = breed.description ?: "",
                overflow = TextOverflow.Ellipsis,
                maxLines = 6,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Preview
@Composable
fun SearchItemPrev() {
    SearchItem(
        breed = CatBreedUiModel.PREVIEW,
        onNavigateToCatBreedDetail = {}
    )
}