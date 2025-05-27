package app.mastani.cathashtad.designSystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QueryFilter(
    modifier: Modifier = Modifier,
    queries: List<String>,
    selectedQuery: String,
    onQueryChanged: (String) -> Unit
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(queries) { query ->
            ElevatedFilterChip(
                elevation = FilterChipDefaults.elevatedFilterChipElevation(elevation = 8.dp),
                onClick = {
                    onQueryChanged(query)
                },
                label = {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(vertical = 6.dp),
                        text = query,
                        style = MaterialTheme.typography.titleSmall
                    )
                },
                selected = selectedQuery == query,
                leadingIcon = if (selectedQuery == query) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "Check icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )
        }
    }
}