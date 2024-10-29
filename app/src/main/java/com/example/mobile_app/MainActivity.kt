package com.example.mobile_app

import MuralCriacao
import MuralEdicao
import MuralListagem
import android.content.Context

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.homepage.visaocliente.componentes.muralcomponentes.MuralViewModel
import com.example.mobile_app.visaocliente.pages.MuralAvisos


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           MuralAvisos()
        }
    }
}

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()
    val muralViewModel: MuralViewModel = viewModel()
    NavHost(navController = navController, startDestination = "muralListagem") {
        composable("muralListagem") { MuralListagem(navController) }
        composable("muralEdicao/{id}/{titulo}/{descricao}") { backStackEntry ->
            val idString = backStackEntry.arguments?.getString("id")
            val id = idString?.toLongOrNull() // Converte ou retorna null se falhar
            val titulo = backStackEntry.arguments?.getString("titulo")
            val descricao = backStackEntry.arguments?.getString("descricao")

            // Verificando se algum dos parâmetros é nulo
            if (id == null || titulo == null || descricao == null) {
                Log.e("Navegacao", "Erro: id, titulo ou descricao é nulo")
                return@composable // ou redirecionar para outra tela
            }

            MuralEdicao(
                id = id,
                titulo = titulo,
                descricao = descricao,
                barbeiroId = 1,
                url = "https://www.youtube.com",
                navController = navController
            )
        }
        composable("muralCriacao") {
            MuralCriacao(
                navController = navController,
                viewModel = muralViewModel,
                context = context
            )
        }
    }
}


