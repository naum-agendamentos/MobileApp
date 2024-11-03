package com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_servico.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servico.ver_servicos.componente.CardServico

@Composable
fun Servicos(viewModel: ServicosViewModel = viewModel(), navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    val servicos = remember { viewModel.servicos }
    val isLoading by viewModel.isLoading // Atualização aqui



    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        navBarb()


        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 90.dp, start = 74.dp, end = 35.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                androidx.compose.material3.Text(
                    text = "SERVIÇOS",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            androidx.compose.material3.Button(
                onClick = { navController.navigate("cadastrar_servico") },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(30.dp)
                    .border(3.dp, Color.White, CircleShape)
            ) {
                androidx.compose.material3.Text(
                    text = "+",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -10.dp)
                .width(350.dp)
                .height(550.dp)
                .background(Color(0x0FFFFFFF), shape = RoundedCornerShape(15.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Use LaunchedEffect para recarregar serviços ao entrar na tela
            LaunchedEffect(Unit) {
                val idBarbearia = 1L
                viewModel.getServicos(idBarbearia)
            }

            if (isLoading) {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier
                )
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(servicos) { servico ->
                        Log.d("TelaServicos", "ID do serviço: ${servico.id}")
                        CardServico(servico = servico) {
                            val servicoJson = """{"id": ${servico.id}, "nomeServico": "${servico.nomeServico}", "preco": "${servico.preco}", "tempoServico": "${servico.tempoServico}"}"""
                            val encodedServicoJson = Uri.encode(servicoJson)
                            navController.navigate("editar_servico/${servico.id}/$encodedServicoJson") // Navegando com ID
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    IconRow(navController = navController, activeIcon = R.drawable.pngtesoura)
}


@Preview(showBackground = true)
@Composable
fun PreviewServicos() {

    // Navegação mockada
    val navController = rememberNavController()

    Servicos(viewModel = viewModel(), navController = navController)
}
