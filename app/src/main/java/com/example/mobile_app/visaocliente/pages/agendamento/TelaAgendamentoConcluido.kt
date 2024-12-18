package com.example.mobile_app.visaocliente.pages.agendamento

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.mobile_app.R

@Composable
fun AgendamentoConcluido() {
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

        Image(
            painter = painterResource(id = R.drawable.logobarbeiro),
            contentDescription = "Login image",
            modifier = Modifier
                .size(60.dp)
                .padding(start = 5.dp)
                .padding(top = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "AGENDAMENTO",
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
                        color = Color.White.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = "AGENDAMENTO",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )

                Text(
                    text = "CONCLUIDO COM",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )

                Text(
                    text = "SUCESSO",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.check2),
                    contentDescription = "Ícone de verificação",
                    modifier = Modifier
                        .size(130.dp)
                        .padding(bottom = 32.dp)
                        .offset(y = 80.dp)
                )

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF003CFF),
                        contentColor = Color.White

                    ),
                    modifier = Modifier
                        .padding(top = 140.dp)
                        .height(50.dp)
                        .width(100.dp)
                ) {
                    Text(text = "OK")
                }
            }

            Text(
                text = "10H00",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )


        }
    }
    //IconRow()
}


@Preview
@Composable
fun Agend() {
    AgendamentoConcluido()
}
