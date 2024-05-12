package com.seiko.demo.voyager_crash_of_kotlin2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.SlideTransition
import com.seiko.demo.voyager_crash_of_kotlin2.ui.theme.Voyagercrashofkotlin2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Voyagercrashofkotlin2Theme {
                Navigator(HomeScreen) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}

object HomeScreen : Screen {
    private fun readResolve(): Any = HomeScreen

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            ) {
                Button(onClick = { navigator.push(DetailScreen) }) {
                    Text("detail (crash)")
                }
                Button(onClick = { navigator.push(DataDetailScreen) }) {
                    Text("detail")
                }
            }
        }
    }
}

object DetailScreen : Screen {
    private fun readResolve(): Any = DetailScreen

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = { navigator.pop() }) {
                    Text("back")
                }
            }
        }
    }
}

data object DataDetailScreen : Screen {
    private fun readResolve(): Any = DataDetailScreen

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = { navigator.pop() }) {
                    Text("back")
                }
            }
        }
    }
}