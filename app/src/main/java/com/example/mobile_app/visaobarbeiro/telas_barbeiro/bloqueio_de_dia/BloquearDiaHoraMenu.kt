package com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.ui.theme.mobile_appTheme
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb

@Composable
fun BloqueioDiaHoraMenu(navController: NavController, semanaJson: String?, id: String) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    Log.e("Semana", semanaJson!!)
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        navBarb(navController = navController)
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.bloquear_dia),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally, // Centraliza horizontalmente
                verticalArrangement = Arrangement.SpaceBetween // Espaça os elementos uniformemente
            ) {
                Box(
                    modifier = Modifier
                        .width(287.5.dp) // 250 dp + 15%
                        .height(143.75.dp) // 125 dp + 15%
                        .padding(20.dp)
                        .background(
                            color = colorResource(id = R.color.preto),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clickable {
                            Log.e("Aviso antes de mandar", semanaJson)
                            navController.navigate("/semana/$id/${Uri.encode(semanaJson)}")
                        },
                    contentAlignment = Alignment.Center // Centraliza o conteúdo no Box
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pngcalendario),
                        contentDescription = "Calendar image",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(bottom = 25.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.bloquear_dia),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(top = 55.dp)
                    )
                }

//                Box(
//                    modifier = Modifier
//                        .width(287.5.dp) // 250 dp + 15%
//                        .height(143.75.dp) // 125 dp + 15%
//                        .padding(20.dp)
//                        .background(
//                            color = colorResource(id = R.color.preto),
//                            shape = RoundedCornerShape(15.dp)
//                        ).clickable {
//                            // Ação ao clicar no Box
//                            println("Box clicado!") // Exemplo de ação
//                        },
//                    contentAlignment = Alignment.Center, // Centraliza o conteúdo no Box
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.pngrelogio),
//                        contentDescription = "Calendar image",
//                        modifier = Modifier
//                            .size(60.dp)
//                            .padding(bottom = 25.dp)
//                    )
//                    Text(
//                        text = "BLOQUEAR HORÁRIO",
//                        style = TextStyle(
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 20.sp,
//                            color = Color.White
//                        ),
//                        modifier = Modifier.padding(top = 55.dp)
//                    )
//                }
            }

        }
        IconRow(navController = navController, activeIcon = R.drawable.pngrelogio)
    }
}

@Preview(showBackground = true)
@Composable
fun BloqueioDiaHoraMenuPreview() {
    mobile_appTheme {
        val navController = rememberNavController()
        BloqueioDiaHoraMenu(navController = navController, "semanaJson", "1")
    }
}