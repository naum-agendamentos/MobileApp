import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.visaobarbeiro.TelaInicial
import com.example.mobile_app.visaobarbeiro.telas_servico.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.Servicos
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeiros
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeirosAgendamento
import com.example.mobile_app.visaobarbeiro.telas_agendamento.agendamento_barbeiro.AgendamentoBarbeiro

@Composable
fun NavGraph(startDestination: String = "tela_barbeiros") {
    val navController = rememberNavController()
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

        //Parte servicos
        composable("tela_servicos") {
            Servicos(viewModel = ServicosViewModel(), navController = navController)
        }
        composable("cadastrar_servico") {
            CadastroServico(navController = navController)
        }

        composable("editar_servico/{servicoId}") { backStackEntry ->
            val servicoId = backStackEntry.arguments?.getString("servicoId")?.toLongOrNull()
            Log.d("NavGraph", "ID do servico recebido: $servicoId") // Verificando o ID recebido
            if (servicoId != null) {
                EditServico(navController = navController, servicoId = servicoId)
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
              // AgendamentoBarbeiro(navController = navController, barbeiroId = barbeiroId)
            } else {
                // Lidar com o caso de ID nulo
                Log.e("NavGraph", "ID do agendamento é nulo.")
            }
        }
    }
}
