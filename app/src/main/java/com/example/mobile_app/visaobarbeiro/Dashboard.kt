package com.example.mobile_app.visaobarbeiro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.ui.theme.mobile_appTheme

class Dashboard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            mobile_appTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Dashboard(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BarChart(
    data: List<Float>,
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary
) {
    Canvas(modifier = modifier) {
        val barWidth = size.width / (data.size * 2)
        val maxDataValue = data.maxOrNull() ?: 1f

        data.forEachIndexed { index, value ->
            val barHeight = (value / maxDataValue) * size.height
            drawRect(
                color = barColor,
                topLeft = androidx.compose.ui.geometry.Offset(
                    x = index * 2 * barWidth,
                    y = size.height - barHeight
                ),
                size = androidx.compose.ui.geometry.Size(
                    width = barWidth,
                    height = barHeight
                )
            )
        }
    }
}

@Composable
fun Dashboard(name: String, modifier: Modifier = Modifier) {
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

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Row {
                    navBarb()
                }

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
                                text = "R$210.00",
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
                                    text = "0 ★",
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
                                    text = "0 - 0% que ontem",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))
                }
            }

            // Adicionando os gráficos um embaixo do outro
            items(
                listOf(
                    "GRÁFICO 1" to listOf(10f, 20f, 30f, 40f),
                    "GRÁFICO 2" to listOf(15f, 25f, 35f, 45f),
                    "GRÁFICO 3" to listOf(5f, 15f, 25f, 35f)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp)
                            .background(Color.Transparent)
                            .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                    )
                }
            }

            // Adicionando espaçamento extra no final
            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
    IconRow()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    mobile_appTheme {
        Dashboard("Android")
    }
}