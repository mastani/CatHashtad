package app.mastani.cathashtad

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val label: String? = null, val icon: ImageVector? = null) {
    data object Home : Screen(label = "Home", icon = Icons.Default.Home)
    data object Search : Screen(label = "Search", icon = Icons.Default.Search)
    data object Favorites : Screen(label = "Favorites", icon = Icons.Default.Star)
    data class BreedDetail(val breedId: String) : Screen()
}