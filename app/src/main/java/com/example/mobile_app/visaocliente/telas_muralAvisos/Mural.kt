package com.example.mobile_app.visaocliente.telas_muralAvisos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homepage.visaocliente.componentes.muralcomponentes.MuralViewModel
import com.example.mobile_app.R

@Composable
fun
        Mural(viewModel: MuralViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {
            Text(
                text = stringResource(R.string.title_activity_mural),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Use remember para armazenar os avisos apenas uma vez
        val avisos = remember { viewModel.getAvisos() }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Se a lista de avisos estiver vazia, exiba uma mensagem
            if (avisos.isEmpty()) {
                item { // Use item para adicionar um único elemento
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(5.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Gray)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Nenhum aviso publicado",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            } else {
                items(items = avisos) { item ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(5.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Gray)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = item.titulo ?: "Título não disponível",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Text(
                                    text = item.descricao ?: "Descrição não disponível",
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewMural() {
    Mural()
}
