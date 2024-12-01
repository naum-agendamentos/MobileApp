package com.example.mobile_app.visaocliente.telas_agendamento.servicos_agendamento

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile_app.R
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavHostController
import com.example.mobile_app.visaocliente.componentes.Header
import com.example.mobile_app.visaocliente.componentes.IconRowClient

@Composable
fun ServicoEscolha(viewModel: ServicosViewModelCliente = viewModel(), navController: NavHostController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_cliente)
    val servicos = remember { viewModel.servicos }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Header()

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "AGENDAMENTOS",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(500.dp)
                    .offset(y = -20.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.TopStart
            ) {

                    LaunchedEffect(Unit) {
                        val idBarbearia = 1L
                        viewModel.getServicos(idBarbearia)
                    }

                    LazyColumn(modifier = Modifier
                        .padding(16.dp)) {


                        items(servicos) { servico ->
                            Log.d("TelaServicos", "ID do serviço: ${servico.id}")
                            ServicoItem2(

                                servico,
                                viewModel,
                                isSelected = false
                            )
                            Spacer(modifier = Modifier.padding(5.dp))
                        }
                    }


                    // Adicionando os itens de serviço


                    Box(
                        modifier = Modifier.fillMaxSize() // Preenche toda a tela
                    ) {
                        Button(
                            onClick = { navController.navigate("agendamento")},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), // Define a cor de fundo do botão
                            modifier = Modifier
                                .padding(
                                    start = 16.dp,
                                    bottom = 32.dp
                                ) // Ajusta o espaçamento para baixo e para a esquerda
                                .align(Alignment.BottomEnd) // Posiciona o botão no canto inferior esquerdo
                        ) {
                            Text(text = "Próximo", color = Color.White) // Texto dentro do botão
                        }
                    }
            }
        }
    }
    IconRowClient(navController = navController, activeIcon = R.drawable.pngcalenduser)
}

@Composable
fun ServicoItem2(
    servico: Servico,
    viewModel: ServicosViewModelCliente,
    isSelected: Boolean
) {
    var checked by remember { mutableStateOf(isSelected) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = servico.nomeServico ?: "", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = servico.preco?.toString() ?: "Preço não disponível", fontSize = 14.sp)
            Text(text = servico.tempoServico?.toString() ?: "Tempo não disponível", fontSize = 14.sp)
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (checked) Color.Blue else Color.White,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(2.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                .clickable {
                    checked = !checked
                    viewModel.addServicos(servico)
                },
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Checked",
                    tint = Color.White
                )
            }
        }
    }

}

//@Preview
//@Composable
//fun EscolherServico() {
//    ServicoEscolha()
//}
