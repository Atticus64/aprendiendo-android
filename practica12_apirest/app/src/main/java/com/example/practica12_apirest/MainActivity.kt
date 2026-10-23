package com.example.practica12_apirest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.practica12_apirest.presentation.PokeViewModel
import com.example.practica12_apirest.presentation.PokemonDetailScreen
import com.example.practica12_apirest.presentation.PokemonsByTypeScreen
import com.example.practica12_apirest.presentation.TiposScreen
import com.example.practica12_apirest.ui.theme.Practica12_apirestTheme

class MainActivity : ComponentActivity() {
    private val viewModel: PokeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "tipos") {
                composable("tipos") {
                    TiposScreen(viewModel) { id ->
                        navController.navigate("pokemons/$id")

                    }
                }
                composable("pokemons/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id") ?: ""
                    PokemonsByTypeScreen(viewModel, id) { pokeId ->
                        navController.navigate("detalle/$pokeId")

                    }
                }
                composable("detalle/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id") ?: ""
                    PokemonDetailScreen(viewModel, id)
                }
            }

        }
    }
}
