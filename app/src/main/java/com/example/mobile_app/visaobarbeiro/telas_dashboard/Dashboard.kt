package com.example.mobile_app.visaobarbeiro.telas_dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb

@Composable
fun BarChart(
    data: List<Float>,
    labels: List<String>,
    modifier: Modifier = Modifier,
    barColors: List<Color> // Lista de cores para as barras
) {
    Canvas(modifier = modifier) {
        val barWidth = size.width / (data.size * 2) // Reduzindo o tamanho das barras
        val maxDataValue = data.maxOrNull() ?: 1f

        // Calcula o espaçamento entre as barras
        val spacing = size.width / (data.size + 1)

        data.forEachIndexed { index, value ->
            val barHeight = (value / maxDataValue) * size.height

            // Desenha a barra com sombra e borda
            drawRect(
                color = barColors.getOrElse(index) { Color.Gray },
                topLeft = androidx.compose.ui.geometry.Offset(
                    x = (index + 1) * spacing - barWidth / 2, // Centraliza a barra
                    y = size.height - barHeight
                ),
                size = androidx.compose.ui.geometry.Size(
                    width = barWidth,
                    height = barHeight
                ),
                style = androidx.compose.ui.graphics.drawscope.Fill
            )

            // Sombra na barra
            drawRect(
                color = Color.Black.copy(alpha = 0.2f), // Cor da sombra
                topLeft = androidx.compose.ui.geometry.Offset(
                    x = (index + 1) * spacing - barWidth / 2 + 5, // Sombra ligeiramente deslocada
                    y = size.height - barHeight + 5 // Sombra ligeiramente deslocada
                ),
                size = androidx.compose.ui.geometry.Size(
                    width = barWidth,
                    height = barHeight
                ),
                style = androidx.compose.ui.graphics.drawscope.Fill
            )

            // Desenha o nome abaixo da barra, ajustando a posição para evitar sobreposição
            val labelX = (index + 1) * spacing // Centraliza o rótulo abaixo da barra
            val labelY = size.height + 40 // Move o rótulo um pouco mais para baixo

            val truncatedLabel = if (labels.getOrElse(index) { "" }.length > 5) {
                labels[index].take(5) + "..." // Truncar e adicionar "..."
            } else {
                labels[index]
            }

            drawContext.canvas.nativeCanvas.drawText(
                truncatedLabel,
                labelX,
                labelY,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.WHITE
                    textAlign = android.graphics.Paint.Align.CENTER
                    textSize = 30f
                    typeface = android.graphics.Typeface.DEFAULT_BOLD // Negrito
                }
            )

            // Desenha o valor dentro da barra, se não for zero
            if (value != 0f) {
                val valueX = (index + 1) * spacing // Centraliza o valor dentro da barra
                val valueY = size.height - barHeight + 40 // Move o valor um pouco mais para baixo

                drawContext.canvas.nativeCanvas.drawText(
                    "${value.toInt()}", // Exibe o valor inteiro dentro da barra
                    valueX,
                    valueY,
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.WHITE
                        textAlign = android.graphics.Paint.Align.CENTER
                        textSize = 30f
                        typeface = android.graphics.Typeface.DEFAULT_BOLD // Negrito
                    }
                )
            }
        }
    }
}

@Composable
fun Dashboard(viewModel: DashboardViewModel = viewModel(), navController: NavHostController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    LaunchedEffect(Unit) {
        viewModel.barbeiroQtdCortes()
        viewModel.lucroPorBarbeiro()
        viewModel.topServicos()
        viewModel.obterLucro()
        viewModel.totalAgendamentosHoje()
        viewModel.obterMediaAvaliacao()
        viewModel.porcentagemAgendOntemHoje()
    }

    val lucroTotal = remember { viewModel.lucro }
    val mediaAvaliacao = remember { viewModel.mediaAvaliacao }
    val porcAgendOntHj = remember { viewModel.porcAgendOntemHj }
    val totalAgendDia = remember { viewModel.totalAgendamentosHoje }
    val barbeirosQtdCortes = remember { viewModel.barbeiros }
    val lucroBarbeiros = remember { viewModel.lucroBarbeiros }
    val topServicos = remember { viewModel.topServicos }

    val porcentagemColor = when {
        porcAgendOntHj.value != null && porcAgendOntHj.value!! > 0 -> Color.Green
        porcAgendOntHj.value != null && porcAgendOntHj.value!! < 0 -> Color.Red
        else -> Color.White
    }

    val porcentagemText = when {
        porcAgendOntHj.value != null && porcAgendOntHj.value!! > 0 -> "+${String.format("%.2f", porcAgendOntHj.value)}%"
        porcAgendOntHj.value != null && porcAgendOntHj.value!! < 0 -> "${String.format("%.2f", porcAgendOntHj.value)}%"
        else -> "0.00%"
    }


    val lucroTotalFormat = if (lucroTotal.value != null) {
        String.format("%.2f", lucroTotal.value)
    } else {
        "0.00"
    }

    val mediaAvaliacaoFormat = if (mediaAvaliacao.value != null) {
        String.format("%.2f", mediaAvaliacao.value)
    } else {
        "0.00"
    }

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

        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 70.dp)
        ) {
            item {


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "DASHBOARD",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(15.dp))

                // Displaying the statistics boxes
                Column(modifier = Modifier.padding(10.dp)) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                            .background(colorResource(id = R.color.preto), RoundedCornerShape(12.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "LUCRO TOTAL DA BARBEARIA",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            )

                            Text(
                                text = "R$$lucroTotalFormat",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                                .background(colorResource(id = R.color.preto), RoundedCornerShape(12.dp))
                                .padding(10.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "MÉDIA DE AVALIAÇÃO",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                )
                                Text(
                                    text = "$mediaAvaliacaoFormat",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                                .background(colorResource(id = R.color.preto), RoundedCornerShape(12.dp))
                                .padding(10.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "TOTAL AGENDAMENTO NO DIA",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                )
                                Text(
                                    text = "${totalAgendDia.value}",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White // Cor padrão para o texto
                                    )
                                )

                                Text(
                                    text = "$porcentagemText que ontem",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = porcentagemColor // A cor é aplicada apenas no valor da porcentagem
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            // Gráfico 1: Quantidade de Cortes por Barbeiro
            items(
                listOf(
                    "Cortes por Barbeiro" to barbeirosQtdCortes.map { it.cortes.toFloat() }
                )
            ) { (title, data) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    )
                    BarChart(
                        data = data,
                        labels = barbeirosQtdCortes.map { it.nome },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp)
                            .background(Color.Transparent)
                            .border(1.dp, Color.White, RoundedCornerShape(5.dp)),
                        barColors = List(data.size) { Color(0xFFFFD700) } // A cor pode ser personalizada
                    )
                }
            }

            // Gráfico 2: Lucro por Barbeiro
            items(
                listOf(
                    "Lucro por Barbeiro" to lucroBarbeiros.map { it.lucro.toFloat() }
                )
            ) { (title, data) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    )
                    BarChart(
                        data = data,
                        labels = lucroBarbeiros.map { it.nome },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp)
                            .background(Color.Transparent)
                            .border(1.dp, Color.White, RoundedCornerShape(5.dp)),
                        barColors = List(data.size) { Color(0xFF00FFFF) } // A cor pode ser personalizada
                    )
                }
            }

            // Gráfico 3: Top Serviços
            items(
                listOf(
                    "Top Serviços" to topServicos.map { it.qtdMes.toFloat() }
                )
            ) { (title, data) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    )
                    BarChart(
                        data = data,
                        labels = topServicos.map { it.nome },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp)
                            .background(Color.Transparent)
                            .border(1.dp, Color.White, RoundedCornerShape(5.dp)),
                        barColors = List(data.size) { Color(0xFF9B59B6) } // A cor pode ser personalizada
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
    IconRow(navController = navController, activeIcon = R.drawable.pnggrafico)
}

