package com.example.mobile_app.visaobarbeiro.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.mobile_app.R

@Composable
fun navBarb(navController: NavController) {
    val expanded = remember { mutableStateOf(false) } // Estado para controlar a visibilidade do menu

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 16.dp) // Adicione um padding superior para empurrar a barra para baixo
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .width(350.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_nav_barb),
                contentDescription = "logo tm",
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 5.dp, top = 10.dp)
                    .clickable {
                        navController.navigate("tela_inicial")
                    }
            )

            Box(
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .offset(y = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pngtrespontos),
                    contentDescription = "Opções",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 5.dp, top = 10.dp)
                        .clickable { expanded.value = true }
                )

                // Menu dropdown
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        navController.navigate("login") // Navega para a tela de login
                    }) {
                        Text("Sair")
                    }
                }
            }
        }
    }
}


