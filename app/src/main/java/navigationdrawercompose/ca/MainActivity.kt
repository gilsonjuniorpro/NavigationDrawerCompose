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
import kotlinx.coroutines.launch
import navigationdrawercompose.ca.ui.DrawerBody
import navigationdrawercompose.ca.ui.DrawerHeader
import navigationdrawercompose.ca.ui.MenuItem
import navigationdrawercompose.ca.ui.theme.NavigationDrawerComposeTheme
import navigationdrawercompose.ca.ui.AppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
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
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to Home screen",
                                    icon = Icons.Default.Home
                                ),
                                MenuItem(
                                    id = "profile",
                                    title = "Profile",
                                    contentDescription = "Go to Profile screen",
                                    icon = Icons.Default.AccountBox
                                ),
                                MenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to Settings screen",
                                    icon = Icons.Default.Settings
                                ),
                            ),
                            onItemClick = {
                                /*when(it.id){
                                    "settings"
                                }*/
                                println("Clicked on ${it.title}")
                            }
                        )
                    }
                ) {
                    
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationDrawerComposeTheme {

    }
}
