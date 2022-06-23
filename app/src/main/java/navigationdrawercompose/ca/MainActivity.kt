package navigationdrawercompose.ca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import navigationdrawercompose.ca.ui.DrawerBody
import navigationdrawercompose.ca.ui.DrawerHeader
import navigationdrawercompose.ca.ui.MenuItem
import navigationdrawercompose.ca.ui.theme.NavigationDrawerComposeTheme
import navigationdrawercompose.ca.ui.AppBar

const val ITEM_HOME = "home"
const val ITEM_PROFILE = "profile"
const val ITEM_SETTINGS = "settings"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ITEM_HOME
                ){
                    composable(ITEM_HOME){
                        Home(navController)
                    }
                    composable(
                        route = ITEM_PROFILE
                    ){
                        Profile(navController)
                    }
                    composable(
                        route = ITEM_SETTINGS
                    ){
                        Settings(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Profile(navController: NavController) {
    Button(onClick = { navController.navigate(ITEM_HOME) }) {
        Text(text = "Profile -> GO TO HOME")
    }
}

@Composable
fun Settings(navController: NavController) {
    Button(onClick = { navController.navigate(ITEM_HOME) }) {
        Text(text = "Settings -> GO TO HOME")
    }
}

@Composable
fun Home(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = ITEM_HOME,
                        title = "Home",
                        contentDescription = "Go to Home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = ITEM_PROFILE,
                        title = "Profile",
                        contentDescription = "Go to Profile screen",
                        icon = Icons.Default.AccountBox
                    ),
                    MenuItem(
                        id = ITEM_SETTINGS,
                        title = "Settings",
                        contentDescription = "Go to Settings screen",
                        icon = Icons.Default.Settings
                    ),
                ),
                onItemClick = {
                    when(it.id){
                        ITEM_HOME -> {
                            navController.navigate(ITEM_HOME)
                        }
                        ITEM_PROFILE -> {
                            navController.navigate(ITEM_PROFILE)
                        }
                        ITEM_SETTINGS -> {
                            navController.navigate(ITEM_SETTINGS)
                        }
                    }
                    println("Clicked on ${it.title}")
                }
            )
        }
    ) {
        Button(onClick = { navController.navigate(ITEM_PROFILE) }) {
            Text(text = "GO TO PROFILE")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationDrawerComposeTheme {

    }
}
