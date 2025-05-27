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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.mastani.cathashtad.designSystem.CustomizableTopBar
import app.mastani.cathashtad.designSystem.SnackBarHost
import app.mastani.cathashtad.designSystem.SnackbarMessage
import app.mastani.cathashtad.designSystem.SnackbarVisualsWithType
import app.mastani.cathashtad.designSystem.StatusBar
import app.mastani.cathashtad.designSystem.theme.CatHashtadTheme
import app.mastani.cathashtad.features.R
import app.mastani.cathashtad.features.breedDetail.screen.navigation.breedDetailScreen
import app.mastani.cathashtad.features.breedDetail.screen.navigation.navigateToBreedDetail
import app.mastani.cathashtad.features.favorites.screen.navigation.favoritesScreen
import app.mastani.cathashtad.features.home.screen.navigation.homeScreen
import app.mastani.cathashtad.features.search.screen.navigation.searchScreen
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
    var selectedBottomNavigationIndex by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

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
        navController.navigateToBreedDetail(breedId)
        onDismissSnackbar()
    }

    var isOnTopLevel by remember { mutableStateOf(true) }
    navController.addOnDestinationChangedListener { controller, destination, _ ->
        isOnTopLevel = TOP_LEVEL_ROUTES.firstOrNull { it.route == destination.route } != null
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
                if (isOnTopLevel)
                    MainToolbar(
                        isDarkTheme = isDarkTheme,
                        toggleDarkTheme = toggleDarkTheme
                    )
                else
                    SubToolbar(
                        title = "",
                        onBack = {
                            navController.popBackStack()
                        }
                    )
            },
            bottomBar = {
                if (isOnTopLevel)
                    BottomNavigationBar(
                        navController = navController,
                        selectedBottomNavigationIndex = selectedBottomNavigationIndex,
                        selectBottomNavigationIndex = {
                            selectedBottomNavigationIndex = it
                        }
                    )
            }
        ) { innerPadding ->
            StatusBar(
                color = if (isDarkTheme) Color.Black else Color.White,
                isDarkTheme = isDarkTheme
            )

            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                homeScreen(
                    onDisplayMessage = onDisplayMessage,
                    onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                )

                searchScreen(
                    onDisplayMessage = onDisplayMessage,
                    onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                )

                favoritesScreen(
                    onDisplayMessage = onDisplayMessage,
                    onNavigateToCatBreedDetail = onNavigateToCatBreedDetail
                )

                breedDetailScreen(
                    onDisplayMessage = onDisplayMessage,
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    selectedBottomNavigationIndex: Int,
    selectBottomNavigationIndex: (Int) -> Unit
) {

    NavigationBar {
        TOP_LEVEL_ROUTES.forEachIndexed { index, screen ->
            val isSelected = selectedBottomNavigationIndex == index

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    selectBottomNavigationIndex(index)
                    navController.navigate(screen.route)
                },
                label = {
                    screen.label?.let {
                        Text(
                            modifier = Modifier,
                            text = screen.label,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                },
                icon = {
                    screen.icon?.let {
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