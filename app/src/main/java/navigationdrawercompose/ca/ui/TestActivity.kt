package navigationdrawercompose.ca.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import navigationdrawercompose.ca.ui.theme.NavigationDrawerComposeTheme
import navigationdrawercompose.ca.ui.theme.Typography

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
                Setup()
            }
        }
    }
}

@Composable
fun Setup() {
    Box(Modifier.padding(16.dp).background(Color.Cyan)) {
        Column {
            Text(text = "teste", style = Typography.h2)
            Text("teste", style = Typography.h1)
            Row {
                Text("teste")
                Spacer(modifier = Modifier.padding(16.dp))
                Text("teste")
            }
            MyComposable()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationDrawerComposeTheme {
        Setup()
    }
}

@Composable
fun MyComposable(){
    var myValue by remember {
        mutableStateOf(false)
    }

    Button(
            onClick = {myValue = !myValue},
            modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "$myValue")
    }

    ScreenContent()

    val items = mutableListOf<String>()
    for(i in 1..10){
        items.add("line $i")
    }
    LazyColumn {
        items(items) { name->
            Text(text = "$name", modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ScreenContent() {
    var name by remember{ mutableStateOf("") }

    TextField(
            value = name,
            onValueChange = {name = it},
            label = { Text("Name") },
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
    )
}
