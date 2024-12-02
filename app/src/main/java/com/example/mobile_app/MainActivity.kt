package com.example.mobile_app

import BarbeiroCad
import EditServico
import EscolherData
import BarbeiroEdit
import CadastroServico
import MuralAvisos
import MuralViewModel
import android.util.Log
import android.os.Bundle
import android.content.Context
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.activity.enableEdgeToEdge
import com.example.mobile_app.login.Login
import androidx.navigation.compose.NavHost
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile_app.visaobarbeiro.TelaInicial
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaobarbeiro.telas_mural.MuralEdicao
import com.example.mobile_app.visaobarbeiro.telas_mural.MuralCriacao
import com.example.mobile_app.visaobarbeiro.telas_mural.MuralListagem
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeiros
import com.example.mobile_app.visaocliente.telas_editarPerfil.MeuPerfil
import com.example.mobile_app.visaobarbeiro.componentes.BlackBackground
import com.example.mobile_app.visaobarbeiro.telas_servico.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.Servicos
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeirosAgendamento
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.telaBloqueioDeDia
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.BloqueioDiaHoraMenu
import com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento.ServicoEscolha
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.BarbeiroBloqueioDiaHora
import com.example.mobile_app.visaobarbeiro.telas_agendamento.agendamento_barbeiro.AgendamentoBarbeiro
import com.example.mobile_app.visaocliente.TelaInicialCliente
import com.example.mobile_app.visaocliente.cadastro_cliente.Cadastro
import com.example.mobile_app.visaocliente.pages.agendamento.AvaliacaoScreen
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_cliente.AgendamentoCliente
import com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento.ServicosViewModelCliente
import com.example.mobile_app.visaocliente.telas_avaliacao.Avaliacao


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlackBackground {
                MyApp(context = this)
            }
//            ServicoEscolha(navController = rememberNavController(), context = this@MainActivity)

        }

    }
}

@Composable
fun MyApp(context: Context) {
    val navController = rememberNavController()
    val muralViewModel: MuralViewModel = viewModel()
    val barbeirosViewModel: BarbeirosViewModel = viewModel()
    val servicosViewModel: ServicosViewModelCliente = viewModel()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            Login(
                navController = navController,

            )
        }

        //mural visão barbeiro
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
                barbeiroId = UserLoginSession.idBarbeiro!!,
                url = "https://res-console.cloudinary.com/dmgfyyioo/media_explorer_thumbnails/ab186f6871c48c32e4450ae6947f67e1/detailed",
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

        //telas barbeiro
        composable("cadastrar_barbeiro"){
            BarbeiroCad(barbeirosViewModel,navController)
        }

        composable("editar_barbeiro"){
            BarbeiroEdit(barbeirosViewModel,navController,null,"")
        }

        composable("tela_barbeiros"){
            TelaBarbeiros(barbeirosViewModel,navController)
        }

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


        //editar Cliente
        composable("editarPerfil") {
            MeuPerfil(navController = navController)
        }

        //Agendamento Cliente
        composable(
            "agendamento/{servicosIds}",
            arguments = listOf(navArgument("servicosIds") { type = NavType.StringType })
        ) { backStackEntry ->
            val servicosIds = backStackEntry.arguments?.getString("servicosIds")?.split(",")?.map { it.toLong() }
            EscolherData(navController = navController, servicosIds = servicosIds ?: emptyList())
        }

        composable("tela_inicial") {
            TelaInicial(navController = navController)
        }

        composable("tela_inicial_cliente") {
            TelaInicialCliente(navController = navController)
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

        //Agendamentos Barbeiros
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

        composable("buscar-agendamento-cliente/{clienteId}") { backStackEntry ->
            val clienteId = backStackEntry.arguments?.getString("clienteId")?.toLongOrNull()
            Log.d("NavGraph", "ID do cliente recebido: $clienteId") // Verificando o ID recebido
            if (clienteId != null) {
                AgendamentoCliente(navController = navController, clienteId = clienteId)
            } else {
                // Lidar com o caso de ID nulo
                Log.e("NavGraph", "ID do cliente é nulo.")
            }
        }


        composable("tela_escolhaServico"){
            ServicoEscolha(servicosViewModel, navController)
        }

        composable(
            route = "menu-barbeiro-bloqueio"
        ) { backStackEntry ->
            // Passe `idBarbeiro` para a composable
            BarbeiroBloqueioDiaHora(barbeirosViewModel, navController)
        }

        composable(
            route = "/bloqueio/{id}/{semanaJson}",
            arguments = listOf(
                navArgument("semanaJson") { type = NavType.StringType },
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val semanaJson = backStackEntry.arguments?.getString("semanaJson")
            val id = backStackEntry.arguments?.getString("id")!!

            BloqueioDiaHoraMenu(navController, semanaJson, id)
        }

        composable(
            route = "/semana/{id}/{semanaJson}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("semanaJson") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val semanaJson = backStackEntry.arguments?.getString("semanaJson")
            val id = backStackEntry.arguments?.getString("id")!!

            telaBloqueioDeDia(navController, semanaJson, id)
        }

        composable("tela_avaliacao") {
            Avaliacao(navController = navController)
        }

        composable("mural-avisos-cliente"){
            MuralAvisos(navController = navController)
        }

        composable("cliente-editado"){
            MeuPerfil(navController = navController)
        }

        composable("cliente-excluido"){
            Login(navController = navController)
        }

        composable("cadastrar_cliente") {
            Cadastro(navController = navController)
        }
    }
}


