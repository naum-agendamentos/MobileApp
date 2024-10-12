package com.example.mobile_app.visaobarbeiro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import com.example.mobile_app.ui.theme.mobile_appTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun telaBloqueioDeHora() {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    val horarios = gerarHorarios("08:00", "20:00")
    val diasDaSemana = listOf("Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado")
    var diaAtualIndex by remember { mutableStateOf(0) }

    // Mapeia cada dia da semana para uma lista de cores independentes para os horários
    val coresPorDia = remember {
        diasDaSemana.associateWith { mutableStateListOf(*Array(horarios.size) { Color.Transparent }) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        navBarb()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "BLOQUEAR HORÁRIO",
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
                    .height(580.dp)
                    .width(350.dp)
                    .background(color = colorResource(id = R.color.preto), shape = RoundedCornerShape(15.dp)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedButton(
                        onClick = { diaAtualIndex = if (diaAtualIndex == 0) diasDaSemana.lastIndex else diaAtualIndex - 1 },
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent, contentColor = Color.Black)
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Anterior")
                    }
                    Row (modifier = Modifier.width(180.dp), horizontalArrangement = Arrangement.Center)
                    {
                        Text(
                            text = diasDaSemana[diaAtualIndex],
                            fontSize = 24.sp,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                    }
                    OutlinedButton(
                        onClick = { diaAtualIndex = if (diaAtualIndex == diasDaSemana.lastIndex) 0 else diaAtualIndex + 1 },
                        border = BorderStroke(2.dp, Color.Black),
                        shape = RoundedCornerShape(5.dp),
                        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent, contentColor = Color.Black)
                    ) {
                        Icon(Icons.Filled.ArrowForward, contentDescription = "Próximo")
                    }
                }

                LazyColumn(
                    modifier = Modifier.width(350.dp).height(400.dp).padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val coresHoras = coresPorDia[diasDaSemana[diaAtualIndex]] ?: mutableStateListOf()
                    itemsIndexed(horarios) { index, horario ->
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .padding(vertical = 8.dp)
                                .background(coresHoras[index], shape = RoundedCornerShape(10.dp))
                                .border(color = Color.White, width = 3.dp, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp)
                                .clickable {
                                    coresHoras[index] = if (coresHoras[index] == Color.Transparent) Color.Red else Color.Transparent
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = horario,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp, horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.width(160.dp).height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.btn_vermelho), contentColor = Color.White)
                    ) {
                        Text(
                            text = "CANCELAR",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        )
                    }
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.width(160.dp).height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.btn_cadastrar), contentColor = Color.White)
                    ) {
                        Text(
                            text = "SALVAR",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        )
                    }
                }
            }
        }
    }
}


fun gerarHorarios(inicio: String, fim: String): List<String> {
    val listaHorarios = mutableListOf<String>()
    val formato = SimpleDateFormat("HH:mm", Locale.getDefault())
    val calendario = Calendar.getInstance()

    // Define a hora inicial
    calendario.time = formato.parse(inicio) ?: return listaHorarios
    // Define a hora final
    val horaFinal = formato.parse(fim) ?: return listaHorarios

    // Gera horários de acordo com o intervalo
    while (calendario.time.before(horaFinal)) {
        listaHorarios.add(formato.format(calendario.time))
        calendario.add(Calendar.MINUTE, 30) // Ajuste para 30 minutos
    }
    return listaHorarios
}


@Preview(showBackground = true)
@Composable
fun BloqueioHoraPreview() {
    mobile_appTheme {
        telaBloqueioDeHora()
    }
}