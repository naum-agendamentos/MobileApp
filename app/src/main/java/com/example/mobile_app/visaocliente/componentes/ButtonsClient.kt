package com.example.mobile_app.visaocliente.componentes

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
import com.example.mobile_app.login.UserLoginSession

@Composable
fun IconRowClient(navController: NavController, activeIcon: Int) {
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
                    .background(Color(0xFF8C8C8C))
                    .width(360.dp)
                    .height(70.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val icons = listOf(
                    R.drawable.pngalertauser to "mural-avisos-cliente",
                    R.drawable.pngcalenduser to "buscar-agendamento-cliente/${UserLoginSession.idCliente}",
                    R.drawable.pngestrela to "tela_avaliacao",
                    R.drawable.pnguser to "editarPerfil",
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
    val backgroundColor = if (isActive) Color(0xFF8C91FF) else Color(0xFFD9D9D9) // Altera a cor de fundo com base na atividade

    Box(
        modifier = Modifier
            .offset(y = -40.dp)
            .size(50.dp)
            .background(backgroundColor, shape = RoundedCornerShape(15.dp))
            .border(1.dp, Color(0xFFD9D9D9), shape = RoundedCornerShape(15.dp))
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
