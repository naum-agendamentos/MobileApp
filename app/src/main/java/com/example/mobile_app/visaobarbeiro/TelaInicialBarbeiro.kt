package com.example.mobile_app.visaobarbeiro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.navBarb

@Composable
fun TelaInicial(navController: NavController) {

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

        navBarb()

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "SEJA BEM VINDO, CARLOS!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White
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
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    // Definindo os botões e suas rotas
                    val buttons = listOf(
                        R.drawable.pngcalendario to Pair("AGENDAMENTOS", "telas_barbeiros_agendamento"),
                        R.drawable.pngbarbeiros to Pair("BARBEIROS", "tela_barbeiros"),
                        R.drawable.pngtesoura to Pair("SERVIÇOS", "tela_servicos"),
                        R.drawable.pngrelogio to Pair("HORÁRIO", "tela_relogio"),
                        R.drawable.pngalerta to Pair("MURAL", "tela_alerta"),
                        R.drawable.pnggrafico to Pair("MEUS DADOS", "tela_grafico")
                    )

                    buttons.chunked(2).forEach { rowButtons ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            rowButtons.forEach { (iconRes, pair) ->
                                val (buttonText, route) = pair
                                Box(
                                    modifier = Modifier
                                        .width(159.dp)
                                        .height(133.dp)
                                        .offset(y = 20.dp)
                                        .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))
                                        .clickable { navController.navigate(route) } // Navegação
                                ) {
                                    Image(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .offset(y = -10.dp)
                                            .width(58.dp)
                                            .height(62.28.dp)
                                    )
                                    Text(
                                        text = buttonText, // Usando texto fixo
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier
                                            .align(Alignment.TopCenter)
                                            .padding(top = 105.dp)
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

@Preview(showBackground = false)
@Composable
fun TelaInicialBarbeiro() {
    // Para visualizar a tela, o NavController deve ser fornecido em um ambiente de navegação.
    // Considerar a utilização de um Preview que simule a navegação ou um ambiente de teste.
}
