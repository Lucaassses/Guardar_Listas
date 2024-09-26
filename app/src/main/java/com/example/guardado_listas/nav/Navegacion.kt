package com.example.guardado_listas.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.*

@Composable
fun navigationExample()
{
    val navController = rememberNavController()

    var usuarios by remember { mutableStateOf(listOf<String>()) }

    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController)
        }
        composable(
            "screen_b/{nombre}/{apellido}/{edad}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("apellido") { type = NavType.StringType },
                navArgument("edad") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val apellido = backStackEntry.arguments?.getString("apellido") ?: ""
            val edad = backStackEntry.arguments?.getString("edad") ?: ""



            val nuevoUsuario = "$nombre $apellido, Edad: $edad"
            if (!usuarios.contains(nuevoUsuario)) {
                usuarios = usuarios + nuevoUsuario
            }

            ScreenB(navController, usuarios)

        }
    }
}