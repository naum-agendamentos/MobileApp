package com.example.homepage.telasguilherme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.homepage.visaocliente.componentes.FundoTela
import com.example.homepage.visaocliente.componentes.meuperfilcomponentes.ExcluirContaDecisao2
import com.example.homepage.visaocliente.componentes.muralcomponentes.Mural

@Composable
fun Exclusao() {
    FundoTela {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.White.copy(alpha = 0.5f),
            shadowElevation = 3.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.75f)
                    .background(color = Color.White.copy(0.5f))
                    .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1f),
                    verticalArrangement = Arrangement.SpaceBetween // Para alinhar os botões no final
                ) {
                    ExcluirContaDecisao2()

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp), // Adiciona espaçamento ao redor dos botões
                        horizontalArrangement = Arrangement.SpaceEvenly // Distribui os botões igualmente
                    ) {
                        Button(
                            onClick = { /* Ação para cancelar */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black, // Fundo transparente
                                contentColor = Color.White // Texto em preto
                            ),
                            border = BorderStroke(1.dp, Color.Black)
                        ) {
                            Text(text = "Cancelar")
                        }

                        Button(
                            onClick = { /* Ação para excluir */ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red, // Fundo vermelho
                                contentColor = Color.White // Texto em branco
                            )
                        ) {
                            Text(text = "Excluir")
                        }
                    }
                }
            }
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun tela(){
    Exclusao()
}