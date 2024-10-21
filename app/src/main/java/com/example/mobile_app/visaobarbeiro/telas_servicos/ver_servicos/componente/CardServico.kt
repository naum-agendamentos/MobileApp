package com.example.mobile_app.visaobarbeiro.telas_servicos.ver_servicos.componente

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardServico(servico: Servico, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        backgroundColor = Color.DarkGray,
        modifier = Modifier
            .width(318.dp) // Define a largura desejada
            .height(72.dp) // Define a altura desejada
            .padding(vertical = 2.9.dp)
            .border(1.dp, Color.White, RoundedCornerShape(15.dp))
            .clickable(onClick = onClick) // Adicionando a ação de clique
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
                    text = servico.nomeServico,
                    fontSize = 18.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "${servico.tempoServico} min",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Text(
                text = "R$ ${servico.preco}",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}