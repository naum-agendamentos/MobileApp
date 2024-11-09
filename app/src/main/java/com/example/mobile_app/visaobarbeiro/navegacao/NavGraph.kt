import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobile_app.visaobarbeiro.TelaInicial
import com.example.mobile_app.visaobarbeiro.telas_servico.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.Servicos
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeiros
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeirosAgendamento
import com.example.mobile_app.visaobarbeiro.telas_agendamento.agendamento_barbeiro.AgendamentoBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.BarbeiroBloqueioDiaHora
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.BloqueioDiaHoraMenu
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.telaBloqueioDeDia

@Composable
fun NavGraph(startDestination: String = "tela_barbeiros") {
    val navController = rememberNavController()
    val barbeirosViewModel: BarbeirosViewModel = viewModel()

    NavHost(navController = navController, startDestination = "tela_inicial") {
        composable("tela_inicial") {
            TelaInicial(navController = navController)
        }

        //parte barbeiro
        composable("tela_barbeiros") {
            TelaBarbeiros(navController = navController)
        }
        composable("cadastrar_barbeiro") { // Adicionando a tela de cadastro
            BarbeiroCad(navController = navController) // Supondo que essa seja a sua tela de cadastro
        }
        composable("editar_barbeiro/{barbeiroId}/{barbeiroJson}") { backStackEntry ->
            val barbeiroId = backStackEntry.arguments?.getString("barbeiroId")?.toLongOrNull()
            val barbeiroJson = backStackEntry.arguments?.getString("barbeiroJson")

            Log.d("NavGraph", "ID do barbeiro recebido: $barbeiroId") // Verificando o ID recebido
            Log.d("NavGraph", "JSON do barbeiro recebido: $barbeiroJson") // Verificando o JSON recebido

            if (barbeiroId != null) {
                // Chama o componente BarbeiroEdit passando ID e JSON
                BarbeiroEdit(navController = navController, barbeiroId = barbeiroId, barbeiroJson = barbeiroJson)
            } else {
                // Lidar com o caso de ID nulo
                Log.e("NavGraph", "ID do barbeiro é nulo.")
            }
        }

        //Parte servicos
        composable("tela_servicos") {
            Servicos(viewModel = ServicosViewModel(), navController = navController)
        }
        composable("cadastrar_servico") {
            CadastroServico(navController = navController)
        }

        composable("editar_servico/{servicoId}/{servicoJson}") { backStackEntry ->
            val servicoId = backStackEntry.arguments?.getString("servicoId")?.toLongOrNull()
            val servicoJson = backStackEntry.arguments?.getString("servicoJson")
            Log.d("NavGraph", "ID do servico recebido: $servicoId") // Verificando o ID recebido
            Log.d("NavGraph", "JSON do servico recebido: $servicoJson") // Verificando o JSON recebido
            if (servicoId != null) {
                EditServico(navController = navController, servicoId = servicoId, servicoJson = servicoJson)
            } else {
                // Lidar com o caso de ID nulo
                Log.e("NavGraph", "ID do serviço é nulo.")
            }
        }

        //parte-agendamentos
        composable("telas_barbeiros_agendamento") {
            TelaBarbeirosAgendamento(navController = navController)
        }

        composable("buscar-agendamento/{barbeiroId}") { backStackEntry ->
            val barbeiroId = backStackEntry.arguments?.getString("barbeiroId")?.toLongOrNull()
            Log.d("NavGraph", "ID do agendamento recebido: $barbeiroId") // Verificando o ID recebido
            if (barbeiroId != null) {
              AgendamentoBarbeiro(navController = navController, barbeiroId = barbeiroId)
            } else {
                // Lidar com o caso de ID nulo
                Log.e("NavGraph", "ID do agendamento é nulo.")
            }
        }
//
//        composable(
//            route = "menu-barbeiro-bloqueio"
//        ) { backStackEntry ->
//            // Passe `idBarbeiro` para a composable
//            BarbeiroBloqueioDiaHora(barbeirosViewModel, navController)
//        }
//
//        composable(
//            route = "/bloqueio/{barbeiroJson}",
//            arguments = listOf(navArgument("barbeiroJson") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val barbeiroJson = backStackEntry.arguments?.getString("barbeiroJson")
//            if (barbeiroJson != null) BloqueioDiaHoraMenu(navController, barbeiroJson)
//        }
//
//        composable(
//            "/semana/{barbeiroJson}"
//        ) { backStackEntry ->
//            val barbeiroJson = backStackEntry.arguments?.getString("barbeiroJson")
//
//            if (barbeiroJson != null) {
//                telaBloqueioDeDia(navController, barbeiroJson)
//            } else {
//                Log.e("NavGraph", "ID do barbeiro é nulo.")
//            }
//        }
    }
}
