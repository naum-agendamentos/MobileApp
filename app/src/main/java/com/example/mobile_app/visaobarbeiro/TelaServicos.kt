package com.example.mobile_app.visaobarbeiro

import Servico
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
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
import com.google.gson.Gson

@Composable
fun Servicos() {
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
                onClick = { /* Adicione a ação desejada aqui */ },
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

            val servicosJson = """
[
    {"nome": "Corte de Cabelo", "tempo": 30, "preco": 50.00},
    {"nome": "Barba", "tempo": 15, "preco": 20.00},
    {"nome": "Coloração", "tempo": 60, "preco": 100.00}
]
"""

            val servicos = Gson().fromJson(servicosJson, Array<Servico>::class.java).toList()

            CardServicos(servicos)


            Spacer(modifier = Modifier.height(16.dp))
        }

    }

    //IconRow()

}

@Composable
fun CardServicos(servicos: List<Servico>) {
    servicos.forEach { servico ->
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
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween 
            ) {

                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = "${servico.nome}",
                        fontSize = 18.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${servico.tempo} min",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

                // Text para preço
                Text(
                    text = "R$ ${servico.preco}",
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Bottom) // Alinha na parte inferior
                )
            }
        }
    }
}




@Preview(showBackground = false)
@Composable
fun PreviewServicos() {
    Servicos()
}
