package com.example.mobile_app.visaocliente.pages.agendamento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R // Aqui você deve substituir pelo seu próprio pacote
import com.example.mobile_app.visaocliente.componentes.FundoTela
import com.example.mobile_app.visaocliente.componentes.Header
import com.example.mobile_app.visaocliente.componentes.IconRowClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val fakeNavController = rememberNavController()
            AvaliacaoScreen(navController = fakeNavController)
        }
    }
}

@Composable
fun AvaliacaoScreen(navController: NavController) {
    var selectedRating by remember { mutableStateOf(0) }



    Column(
        modifier = Modifier
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Header()

        Column(
            modifier = Modifier
                .fillMaxSize()
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            // Título
            Text(
                text = "AVALIAÇÃO",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Imagem da barbearia (substitua por seu próprio recurso)
            Image(
                painter = painterResource(id = R.drawable.logobarbeiro), // Use placeholder no preview
                contentDescription = "Logo da Barbearia",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )

            // Estrelas de avaliação
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 1..5) {
                    IconButton(onClick = { selectedRating = i }) {
                        if (i <= selectedRating) {
                            // Usar ícone vetorial (preenchido quando selecionado)
                            Icon(
                                imageVector = Icons.Filled.Star, // Ícone vetorial padrão
                                contentDescription = null,
                                tint = Color(0xFFFFD700), // Dourado para estrelas selecionadas
                                modifier = Modifier.size(40.dp)
                            )
                        } else {
                            // Usar ícone vetorial padrão para estrelas não selecionadas
                            Icon(
                                imageVector = Icons.Filled.Star, // Usar mesmo ícone no preview
                                contentDescription = null,
                                tint = Color.Gray, // Cinza para estrelas não selecionadas
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão Avaliar
            Button(
                onClick = { /* Ação ao clicar no botão */ },
                modifier = Modifier
                    .width(150.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0047AB))
            ) {
                Text(
                    text = "AVALIAR",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    IconRowClient(navController = navController, activeIcon = R.drawable.pngestrela)
}

@Preview(showBackground = true)
@Composable
fun AvaliacaoScreenPreview() {
    val fakeNavController = rememberNavController()
    AvaliacaoScreen(navController = fakeNavController)
}

