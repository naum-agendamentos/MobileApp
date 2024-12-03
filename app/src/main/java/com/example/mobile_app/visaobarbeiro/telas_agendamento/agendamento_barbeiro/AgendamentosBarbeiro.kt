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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.ui.draw.shadow
import java.time.LocalDate
import java.time.LocalDateTime
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
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)



    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        navBarb(navController = navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp)
        ) {
            androidx.compose.material3.Text(
                text = "AGENDAMENTOS",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Cabeçalho para seleção de data
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(350.dp)
                    .height(70.dp)
                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { selectedDate = selectedDate.minusDays(1) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = "<", color = Color.Black)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = viewModel.agendamentos.firstOrNull()?.barbeiro?.nome ?: "",
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Text(
                            text = selectedDate.format(dateFormatter),
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    }

                    Button(
                        onClick = { selectedDate = selectedDate.plusDays(1) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                    ) {
                        Text(text = ">", color = Color.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .offset(y = -10.dp)
                    .width(350.dp)
                    .height(550.dp)
                    .background(Color(0x0FFFFFFF), shape = RoundedCornerShape(15.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.Black)
                } else if (errorMessage != null) {
                    Text(text = "Erro: $errorMessage", color = Color.Red)
                } else {
                    val agendamentosFiltrados = viewModel.getAgendamentosPorData(selectedDate)

                    if (agendamentosFiltrados.isEmpty()) {
                        Text(text = "Nenhum agendamento encontrado", fontSize = 18.sp, color = Color.White)
                    } else {
                        // Ordena os agendamentos pelo horário
                        val agendamentosOrdenados = agendamentosFiltrados.sortedBy {
                            LocalDateTime.parse(it.dataHoraAgendamento)
                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(1), // Pode ajustar o número de colunas
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(agendamentosOrdenados.size) { index ->
                                val agendamento = agendamentosOrdenados[index]
                                // Convertemos a string para LocalDateTime
                                val dataHoraAgendamento = LocalDateTime.parse(agendamento.dataHoraAgendamento)

                                Card(
                                    shape = RoundedCornerShape(15.dp),
                                    backgroundColor = Color.DarkGray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 2.9.dp)
                                        .border(1.dp, Color.White, RoundedCornerShape(15.dp))
                                        .shadow(8.dp, RoundedCornerShape(15.dp)) // Adiciona sombra ao card
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(16.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            if (agendamento.cliente != null) {
                                                Text(
                                                    text = "${agendamento.cliente.nome}",
                                                    fontSize = 18.sp,
                                                    color = Color.White
                                                )


                                                val servicosVisiveis = agendamento.servicos
                                                Text(
                                                    text = "Serviços: ${
                                                        servicosVisiveis.joinToString(
                                                            ", "
                                                        ) { it.nomeServico ?: "Indisponível" }
                                                    }",
                                                    fontSize = 16.sp,
                                                    color = Color.Gray
                                                )
                                            } else {
                                                Text(
                                                    text = "Horário Bloqueado!",
                                                    color = Color.Yellow
                                                )
                                            }

                                                Spacer(modifier = Modifier.weight(1f))

                                                Text(
                                                    text = dataHoraAgendamento.format(timeFormatter),
                                                    fontSize = 16.sp,
                                                    color = Color.LightGray
                                                )


                                        }


                                        Text(
                                            text = "R$ ${agendamento.valorTotal}",
                                            fontSize = 18.sp,
                                            color = Color.White,
                                            modifier = Modifier.align(Alignment.Bottom)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    IconRow(navController = navController, activeIcon = R.drawable.pngcalendario)
}
