package com.example.homepage.visaobarbeiro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.homepage.R

@Composable
fun VisaoCliente() {

    val backgroundImage = painterResource(id = R.drawable.fundo_cliente)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        NavCliente()


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "SEJA BEM VINDO, CARLOS!",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Black
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
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceEvenly) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .width(159.dp)
                                .height(133.dp)
                                .offset(y = 20.dp)
                                .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pngcalendario),
                                contentDescription = "calendario",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = -10.dp)
                                    .width(58.dp)
                                    .height(62.28.dp)
                            )
                            Text(
                                text = "AGENDAR",
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

                        Box(
                            modifier = Modifier
                                .width(159.dp)
                                .height(133.dp)
                                .offset(y = 20.dp)
                                .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pngbarbeiros),
                                contentDescription = "Avaliar",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = -10.dp)
                                    .width(58.dp)
                                    .height(62.28.dp)
                            )
                            Text(
                                text = "AVALIAR",
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .width(159.dp)
                                .height(133.dp)
                                .offset(y = 20.dp)
                                .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pngtesoura),
                                contentDescription = "Meus Dados",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = -10.dp)
                                    .width(58.dp)
                                    .height(62.28.dp)
                            )
                            Text(
                                text = "MEUS DADOS",
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

                        Box(
                            modifier = Modifier
                                .width(159.dp)
                                .height(133.dp)
                                .offset(y = 20.dp)
                                .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pngrelogio),
                                contentDescription = "Mural",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = -10.dp)
                                    .width(58.dp)
                                    .height(62.28.dp)
                            )
                            Text(
                                text = "MURAL",
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

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .width(159.dp)
                                .height(133.dp)
                                .offset(y = 20.dp)
                                .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pngalerta),
                                contentDescription = "Meus Agendamentos",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = -10.dp)
                                    .width(58.dp)
                                    .height(62.28.dp)
                            )
                            Text(
                                text = "AGENDAMENTOS",
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

                        Box(
                            modifier = Modifier
                                .width(159.dp)
                                .height(133.dp)
                                .offset(y = 20.dp)
                                .background(Color(0xFF3D3D3D), shape = RoundedCornerShape(15.dp))

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pnggrafico),
                                contentDescription = "Barbearia",
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .offset(y = -10.dp)
                                    .width(58.dp)
                                    .height(62.28.dp)
                            )
                            Text(
                                text = "BARBEARIA",
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

@Preview(showBackground = false)
@Composable
fun TelaInicialCliente() {
    VisaoCliente()
}