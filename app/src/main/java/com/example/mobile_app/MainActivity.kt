package com.example.mobile_app

import BarbeiroCad
import BarbeiroEdit
import CadastroServico
import EditServico
import EscolherData
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
import com.example.mobile_app.login.Login
import com.example.mobile_app.visaobarbeiro.TelaInicial
import com.example.mobile_app.visaobarbeiro.componentes.BlackBackground
import com.example.mobile_app.visaobarbeiro.telas_agendamento.agendamento_barbeiro.AgendamentoBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.BarbeiroBloqueioDiaHora
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.BloqueioDiaHoraMenu
//import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.telaBloqueioDeDia
import com.example.mobile_app.visaobarbeiro.telas_mural.MuralCriacao
import com.example.mobile_app.visaobarbeiro.telas_mural.MuralEdicao
import com.example.mobile_app.visaobarbeiro.telas_mural.MuralListagem
import com.example.mobile_app.visaobarbeiro.telas_servico.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.Servicos
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeiros
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.TelaBarbeirosAgendamento
import com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento.ServicoEscolha
import com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento.ServicosViewModelCliente
import com.example.mobile_app.visaocliente.telas_editarPerfil.MeuPerfil


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
                context = context
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
            MeuPerfil()
        }

        //Agendamento Cliente
        composable("agendamento") {
            EscolherData()
        }

        composable("tela_inicial") {
            TelaInicial(navController = navController)
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

        composable("tela_escolhaServico"){
            ServicoEscolha(servicosViewModel, navController)
        }

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
//
//            BloqueioDiaHoraMenu(navController, barbeiroJson)
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
//                Log.e("MainActivity", "ID do barbeiro é nulo.")
//            }
//        }
    }
}


