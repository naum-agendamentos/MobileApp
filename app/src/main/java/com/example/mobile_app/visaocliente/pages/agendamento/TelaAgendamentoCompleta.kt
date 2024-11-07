package com.example.mobile_app.visaocliente.pages.agendamento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import java.time.LocalDate


@Composable
fun SchedulingScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now().dayOfMonth) }
    var selectedTime by remember { mutableStateOf("") }
    val times = listOf("13:00", "13:30", "14:00")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "AGENDAMENTO",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Barbeiros
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(4) { // Aqui, assumindo 4 barbeiros
                BarberProfile(imageId = R.drawable.barbeiro2)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Calendário
        Text(
            text = "AGOSTO - 2024",
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Calendar(
            selectedDate = selectedDate,
            onDateSelected = { date ->
                selectedDate = date
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Horários Disponíveis
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            times.forEach { time ->
                Button(
                    onClick = { selectedTime = time },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (selectedTime == time) Color.Blue else Color.Gray
                    )
                ) {
                    Text(text = time)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botões Inferiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* Ação para o botão anterior */ }) {
                Text(text = "ANTERIOR")
            }

            Button(onClick = { /* Ação para o botão concluir */ }) {
                Text(text = "CONCLUIR")
            }
        }
    }
}

@Composable
fun BarberProfile(imageId: Int) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { /* ação ao clicar no barbeiro */ }
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "Barber",
            modifier = Modifier
                .size(64.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(50)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Calendar(selectedDate: Int, onDateSelected: (Int) -> Unit) {
    val daysInMonth = 31
    val weekDays = listOf("D", "S", "T", "Q", "Q", "S", "S")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Dias da semana
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            weekDays.forEach { day ->
                Text(text = day, fontSize = 18.sp)
            }
        }

        // Dias do mês
        for (week in 0..4) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                for (day in (week * 7 + 1)..(week * 7 + 7)) {
                    if (day <= daysInMonth) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    color = if (day == selectedDate) Color.Blue else Color.Transparent,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onDateSelected(day) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = day.toString())
                        }
                    } else {
                        Spacer(modifier = Modifier.size(40.dp))
                    }
                }
            }
        }
    }
}

// Adicionando o Preview
@Preview(showBackground = true)
@Composable
fun PreviewSchedulingScreen() {
    SchedulingScreen()
}
