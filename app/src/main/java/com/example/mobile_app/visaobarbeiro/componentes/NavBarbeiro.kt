package com.example.mobile_app.visaobarbeiro.componentes

import androidx.compose.foundation.Image
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobile_app.R

@Composable
fun navBarb() {
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
                contentDescription = "Login image",
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 5.dp)
                    .padding(top = 10.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.pngtrespontos),
                contentDescription = "Opções",
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .offset(y = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun verNavBarbeiro() {
    navBarb()
}
