package com.example.mobile_app.visaobarbeiro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R

@Composable
fun CardBarbeiro(name: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .width(154.dp)
            .height(295.dp)
            .border(1.dp, Color.White, shape = RoundedCornerShape(15.dp)) // Adiciona borda ao card
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF424242)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(154.dp)
                        .height(208.dp)
                        .background(Color(0xFF424242)) // Cor de fundo da imagem
                )

                // Adiciona uma borda branca na parte inferior da imagem
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp) // Altura da borda
                        .align(Alignment.BottomCenter)
                        .background(Color.White) // Cor da borda
                )
            }
            Text(
                text = name,
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .offset(y = -40.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun CardBarbeiroPreview() {
    CardBarbeiro(name = "Bryan Liaris", imageRes = R.drawable.barbeiro1)
}

