package com.example.mobile_app.visaobarbeiro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile_app.R

@Composable
fun IconRow() {
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupar toda a tela
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
                    R.drawable.pngbarbeiros,
                    R.drawable.pnggrafico,
                    R.drawable.pngrelogio,
                    R.drawable.pngtesoura,
                    R.drawable.pngcalendario,
                    R.drawable.pngalerta
                )

                icons.forEach { iconRes ->
                    IconBox(iconRes)
                }
            }
        }
    }
}

@Composable
fun IconBox(iconRes: Int) {
    Box(
        modifier = Modifier
            .offset(y = -40.dp)
            .size(50.dp)
            .background(Color(0xFF333333), shape = RoundedCornerShape(15.dp))
            .border(1.dp, Color(0xFF8B8000), shape = RoundedCornerShape(15.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
fun IconRowPreview() {
    IconRow()
}
