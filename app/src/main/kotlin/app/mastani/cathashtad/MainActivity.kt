package app.mastani.cathashtad

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import app.mastani.cathashtad.common.TopLevelBackStack
import app.mastani.cathashtad.designSystem.CustomizableTopBar
import app.mastani.cathashtad.designSystem.SnackBarHost
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.designSystem.SnackbarVisualsWithType
import app.mastani.cathashtad.designSystem.StatusBar
import app.mastani.cathashtad.designSystem.theme.CatHashtadTheme
import app.mastani.cathashtad.features.R
import app.mastani.cathashtad.features.breedDetail.screen.BreedDetailRoute
import app.mastani.cathashtad.features.favorites.screen.FavoriteRoute
import app.mastani.cathashtad.features.home.screen.HomeRoute
import app.mastani.cathashtad.features.search.screen.SearchRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

val TOP_LEVEL_ROUTES: List<Screen> = listOf(Screen.Home, Screen.Search, Screen.Favorites)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val isDarkTheme by viewModel.isDarkTheme.collectAsStateWithLifecycle()

            CatHashtad(
                isDarkTheme = isDarkTheme ?: false,
                toggleDarkTheme = {
                    val appTheme = isDarkTheme ?: false
                    viewModel.setAppTheme(!appTheme)
                }
            )
        }
    }
}

@Composable
fun CatHashtad(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    toggleDarkTheme: () -> Unit = {}
) {
    val topLevelBackStack = remember { TopLevelBackStack(Screen.Home) }

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val onDisplayMessage: (SnackbarMessage?) -> Unit = { snackInfo ->
        snackInfo?.message?.let {
            scope.launch {
                snackBarHostState.showSnackbar(
                    SnackbarVisualsWithType(
                        msg = it,
                        typ = snackInfo.type
                    )
                )
            }
        }
    }
    val onDismissSnackbar = {
        scope.launch { snackBarHostState.currentSnackbarData?.dismiss() }
    }

    val onNavigateToCatBreedDetail: (String) -> Unit = { breedId ->
        onDismissSnackbar()
        topLevelBackStack.add(Screen.BreedDetail(breedId))
    }

    CatHashtadTheme(
        darkTheme = isDarkTheme
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            snackbarHost = {
                SnackBarHost(hostState = snackBarHostState)
            },
            topBar = {
                if (topLevelBackStack.isOnTopLevel())
                    MainToolbar(
                        isDarkTheme = isDarkTheme,
                        toggleDarkTheme = toggleDarkTheme
                    )
                else
                    SubToolbar(
                        title = "",
                        onBack = {
                            topLevelBackStack.removeLast()
                        }
                    )
            },
            bottomBar = {
                if (topLevelBackStack.isOnTopLevel())
                    BottomNavigationBar(topLevelBackStack)
            }
        ) { innerPadding ->
            StatusBar(
                color = if (isDarkTheme) Color.Black else Color.White,
                isDarkTheme = isDarkTheme
            )

            NavDisplay(
                modifier = Modifier.padding(innerPadding),
                entryDecorators = listOf(
                    rememberSceneSetupNavEntryDecorator(),
                    rememberSavedStateNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                ),
                backStack = topLevelBackStack.backStack,
                onBack = { topLevelBackStack.removeLast() },
                entryProvider = entryProvider {
                    entry<Screen.Home> {
                        HomeRoute(
                            onDisplayMessage = onDisplayMessage,
                            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                        )
                    }

                    entry<Screen.BreedDetail> {
                        BreedDetailRoute(
                            onDisplayMessage = onDisplayMessage,
                            breedId = it.breedId
                        )
                    }

                    entry(Screen.Favorites) {
                        FavoriteRoute(
                            onDisplayMessage = onDisplayMessage,
                            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                        )
                    }

                    entry(Screen.Search) {
                        SearchRoute(
                            onDisplayMessage = onDisplayMessage,
                            onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun BottomNavigationBar(
    topLevelBackStack: TopLevelBackStack
) {
    NavigationBar {
        TOP_LEVEL_ROUTES.forEach { topLevelRoute ->
            val isSelected = topLevelRoute == topLevelBackStack.topLevelKey
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    topLevelBackStack.addTopLevel(topLevelRoute)
                },
                label = {
                    topLevelRoute.label?.let {
                        Text(
                            modifier = Modifier,
                            text = topLevelRoute.label,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                },
                icon = {
                    topLevelRoute.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun MainToolbar(
    isDarkTheme: Boolean,
    toggleDarkTheme: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineSmall
        )

        Image(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    toggleDarkTheme()
                },
            painter = painterResource(id = if (isDarkTheme) R.drawable.ic_sun else R.drawable.ic_moon),
            contentDescription = "Toggle Theme",
            colorFilter = ColorFilter.tint(
                if (isDarkTheme) Color.White else Color.Black
            )
        )
    }
}

@Composable
fun SubToolbar(
    title: String,
    onBack: () -> Unit
) {
    CustomizableTopBar(
        title = title,
        leftIconId = app.mastani.cathashtad.designSystem.R.drawable.ic_arrow_left,
        onLeftClick = onBack
    )
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun CatHashtadPreview() {
    CatHashtad()
}