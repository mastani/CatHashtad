package app.mastani.cathashtad

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String? = null, val icon: ImageVector? = null) {
    data object Home : Screen(route = "home", label = "Home", icon = Icons.Default.Home)
    data object Search : Screen(route = "search", label = "Search", icon = Icons.Default.Search)
    data object Favorites : Screen(route = "favorites", label = "Favorites", icon = Icons.Default.Star)
    data class BreedDetail(val breedId: String) : Screen(route = "breedDetail")
}