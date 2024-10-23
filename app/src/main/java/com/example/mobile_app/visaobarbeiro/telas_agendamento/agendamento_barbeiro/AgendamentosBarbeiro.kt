package com.example.mobile_app.visaobarbeiro.telas_agendamento.agendamento_barbeiro

import AgendamentoListagemDto
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.navigation.NavController
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_agendamento.AgendamentosViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AgendamentoBarbeiro(
    viewModel: AgendamentosViewModel = viewModel(),
    navController: NavController,
    barbeiroId: Long
) {
    // Obter os agendamentos com base no barbeiroId
    LaunchedEffect(barbeiroId) {
        viewModel.getAgendamentos(barbeiroId)
    }

    val agendamentos = viewModel.agendamentos
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM")
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    navBarb()

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

        androidx.compose.material3.Text(
            text = "AGENDAMENTOS",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
        )

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

            // Cabeçalho para seleção de data
            Box(modifier = Modifier
                .width(336.dp)
                .height(58.dp)
                .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Botão de dia anterior
                    Box(
                        modifier = Modifier
                            .width(29.dp)
                            .height(29.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                            .align(Alignment.CenterVertically)
                    ) {
                        Button(
                            onClick = { selectedDate = selectedDate.minusDays(1) }, // Corrigir para dia anterior
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "<", color = Color.Black)
                        }
                    }

                    Column {
                        Text(
                            text = "COLOCAR BARBEIRO AQUI",
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = selectedDate.format(dateFormatter),
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    // Botão de dia seguinte
                    Box(
                        modifier = Modifier
                            .width(29.dp)
                            .height(29.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                            .align(Alignment.CenterVertically)
                    ) {
                        Button(
                            onClick = { selectedDate = selectedDate.plusDays(1) },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = ">", color = Color.Black)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(color = Color.Black)
            } else if (errorMessage != null) {
                Text(text = "Erro: $errorMessage", color = Color.Red)
            } else if (agendamentos.isEmpty()) {
                Text(text = "Nenhum agendamento encontrado", color = Color.Black)
            } else {
                agendamentos.forEach { agendamento ->
                    // Montar detalhes do agendamento
                    val clienteNome = agendamento.cliente.nome
                    val servicos = agendamento.servicos.joinToString(", ") { it.nome }
                    val total = agendamento.valorTotal

                    Card(
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = Color.DarkGray,
                        modifier = Modifier
                            .width(318.dp)
                            .height(72.dp)
                            .padding(vertical = 2.9.dp)
                            .border(1.dp, Color.White, RoundedCornerShape(15.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$clienteNome - $servicos",
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.weight(1f)) // Espaço flexível
                            Text(
                                text = "R$ $total",
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }

    IconRow(navController = navController, activeIcon = R.drawable.pngcalendario)
}

