package com.example.mobile_app.visaocliente.pages.agendamento

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.mobile_app.R

@Composable
fun AgendamentosCliente() {
    val backgroundImage = painterResource(id = R.drawable.fundo_cliente)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = "Background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.logobarbeiro),
            contentDescription = "Logo Barbeiro",
            modifier = Modifier
                .size(60.dp)
                .padding(start = 5.dp, top = 10.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "MEUS AGENDAMENTOS",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(150.dp)
                    .offset(y = -170.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Quarta-Feira, 20 de Outubro",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "PROFISSIONAL: BRYAN LIARIS",
                        style = TextStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Exemplos de ícones de ação para editar e cancelar o agendamento
                // IconButton(onClick = { /* Ação de editar */ }) {
                //     Icon(Icons.Filled.Edit, contentDescription = "Editar agendamento")
                // }
                // IconButton(onClick = { /* Ação de cancelar */ }) {
                //     Icon(Icons.Filled.Delete, contentDescription = "Cancelar agendamento")
                // }
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
}

@Preview
@Composable
fun VizuAgendamentosCliente() {
    AgendamentosCliente()
}
