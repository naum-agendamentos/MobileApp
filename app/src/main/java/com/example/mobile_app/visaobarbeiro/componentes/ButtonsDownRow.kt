package com.example.mobile_app.visaobarbeiro.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobile_app.R

@Composable
fun IconRow(navController: NavController, activeIcon: Int) {
    Box(
        modifier = Modifier.fillMaxSize() // Ocupar toda a tela
    ) {
        Column(
            modifier = Modifier.fillMaxSize(), // Ocupar toda a tela
            verticalArrangement = Arrangement.Bottom // Alinhar conteúdo ao fundo
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF333333))
                    .width(360.dp)
                    .height(70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val icons = listOf(
                    R.drawable.pngbarbeiros to "tela_barbeiros",
                    R.drawable.pnggrafico to "tela_grafico",
                    R.drawable.pngrelogio to "tela_relogio",
                    R.drawable.pngtesoura to "tela_servicos",
                    R.drawable.pngcalendario to "telas_barbeiros_agendamento",
                    R.drawable.pngalerta to "muralListagem"
                )

                icons.forEach { (iconRes, route) ->
                    IconBox(iconRes, iconRes == activeIcon) {
                        navController.navigate(route) // Navega para a rota correspondente
                    }
                }
            }
        }
    }
}

@Composable
fun IconBox(iconRes: Int, isActive: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isActive) Color(0xFF8B8000) else Color(0xFF333333) // Altera a cor de fundo com base na atividade

    Box(
        modifier = Modifier
            .offset(y = -40.dp)
            .size(50.dp)
            .background(backgroundColor, shape = RoundedCornerShape(15.dp))
            .border(1.dp, Color(0xFF8B8000), shape = RoundedCornerShape(15.dp))
            .clickable(onClick = onClick), // Adiciona o clique
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}


//@Preview(showSystemUi = false, showBackground = true)
//@Composable
//fun IconRowPreview() {
//    IconRow(activeIcon = R.drawable.pngbarbeiros) // Exemplo de visualização com o ícone "barbeiros" ativo
//}
