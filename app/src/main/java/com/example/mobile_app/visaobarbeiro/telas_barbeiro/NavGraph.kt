import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeiros

@Composable
fun NavGraph(startDestination: String = "tela_barbeiros") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "tela_barbeiros") {
        composable("tela_barbeiros") {
            TelaBarbeiros(navController = navController)
        }
        composable("cadastrar_barbeiro") { // Adicionando a tela de cadastro
            BarbeiroCad(navController = navController) // Supondo que essa seja a sua tela de cadastro
        }
        composable("editar_barbeiro/{barbeiroId}") { backStackEntry ->
            val barbeiroId = backStackEntry.arguments?.getString("barbeiroId")?.toLongOrNull()
            Log.d("NavGraph", "ID do barbeiro recebido: $barbeiroId") // Verificando o ID recebido
            if (barbeiroId != null) {
                BarbeiroEdit(navController = navController, barbeiroId = barbeiroId)
            } else {
                // Lidar com o caso de ID nulo
                Log.e("NavGraph", "ID do barbeiro é nulo.")
            }
        }
    }
}
