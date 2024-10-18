import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.homepage.visaocliente.componentes.muralcomponentes.MuralViewModel
import com.example.homepage.visaocliente.componentes.muralcomponentes.Aviso // Certifique-se de que a importação está correta

@Composable
fun AppNavigation(muralViewModel: MuralViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "muralListagem") {
        composable("muralListagem") {
            MuralListagem(navController, muralViewModel) // Passa o ViewModel para a Listagem
        }
        composable(
            "muralEdicao/{titulo}/{descricao}",
            arguments = listOf(
                navArgument("titulo") { type = NavType.StringType },
                navArgument("descricao") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val titulo = backStackEntry.arguments?.getString("titulo") ?: ""
            val descricao = backStackEntry.arguments?.getString("descricao") ?: ""

            // Configurar o aviso atual na ViewModel
            muralViewModel.itemAtual = Aviso(titulo = titulo, descricao = descricao) // Você pode deixar id e tipoAviso como null

            // Passar o navController e o ViewModel para a tela de edição
            MuralEdicao(titulo = titulo, descricao = descricao, navController = navController, viewModel = muralViewModel)
        }
    }
}
