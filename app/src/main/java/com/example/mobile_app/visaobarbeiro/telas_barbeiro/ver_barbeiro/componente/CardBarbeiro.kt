package com.example.mobile_app.visaobarbeiro.ver_barbeiro.componente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import coil.compose.rememberImagePainter
import com.example.mobile_app.R

@Composable
fun CardBarbeiro(name: String, imageUrl: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(154.dp)
            .height(295.dp)
            .border(1.dp, Color.White, shape = RoundedCornerShape(15.dp))
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick() } // Mantém a funcionalidade de clique
                .fillMaxSize()
                .background(Color(0xFF424242)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(
                        data = imageUrl,
                        builder = {
                            placeholder(R.drawable.placeholder_image)
                            error(R.drawable.error_image)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(154.dp)
                        .height(208.dp)
                        .background(Color(0xFF424242))
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .align(Alignment.BottomCenter)
                        .background(Color.White)
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
    CardBarbeiro(name = "Bryan Liaris", imageUrl = "https://example.com/barbeiro1.jpg", onClick = {})
}
