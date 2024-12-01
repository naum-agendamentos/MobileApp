import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import java.time.LocalDate
import java.time.YearMonth
import com.example.mobile_app.R
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.AgendamentoViewModel
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.DadosCliente
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaocliente.componentes.IconRowClient
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.MonthDay
import java.time.format.DateTimeFormatter


@Composable
fun EscolherData(navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_cliente)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.logobarbeiro),
            contentDescription = "Login image",
            modifier = Modifier
                .size(60.dp)
                .padding(start = 5.dp)
                .padding(top = 10.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "AGENDAMENTOS",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(550.dp)
                    .offset(y = -20.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                SchedulingScreenn(navController)
            }

            Text(
                text = "10H00",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )
        }
        IconRowClient(navController = navController, activeIcon = R.drawable.pngcalenduser)
    }
}

@Composable
fun SchedulingScreenn(navController: NavController) {
    var selectedBarbeiroId by remember { mutableStateOf<Long?>(null) }
    var selectedDate by remember { mutableStateOf(LocalDate.now().dayOfMonth) }
    var selectedMonth by remember { mutableStateOf(LocalDate.now().monthValue) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }

    val currentYear = LocalDate.now().year
    val times = generateTimeSlots(8, 20)
    var currentTimeIndex by remember { mutableStateOf(0) }
    val visibleTimeSlots = 2
    val viewModel: AgendamentoViewModel = viewModel()
    val barbeiros = remember { viewModel.barbeiros }

    val agendamentoViewModel: AgendamentoViewModel = viewModel()


    LaunchedEffect(Unit) {
        viewModel.fetchBarbeiros()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(barbeiros) { barbeiro ->
                barbeiro.foto?.let { imageUrl ->
                    BarberProfiles(
                        imageUrl = imageUrl,
                        onClick = { selectedBarbeiroId = barbeiro.id }
                    )
                }
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { selectedMonth = if (selectedMonth > 1) selectedMonth - 1 else 12 }) {
                Text(text = "<")
            }

            Text(
                text = "${getMonthName(selectedMonth)} - $currentYear",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Button(onClick = { selectedMonth = if (selectedMonth < 12) selectedMonth + 1 else 1 }) {
                Text(text = ">")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Calendar(
            selectedDate = selectedDate,
            selectedMonth = selectedMonth,
            onDateSelected = { date ->
                selectedDate = date
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentTimeIndex > 0) currentTimeIndex -= visibleTimeSlots
                },
                enabled = currentTimeIndex > 0
            ) {
                Text(text = "<")
            }

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                times.drop(currentTimeIndex).take(visibleTimeSlots).forEach { timeText ->
                    val formatter = DateTimeFormatter.ofPattern("H:mm")
                    val time = LocalTime.parse(timeText, formatter)

                    Button(
                        onClick = { selectedTime = time },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedTime == time) Color.Blue else Color.Gray
                        )
                    ) {
                        Text(text = timeText)
                    }
                }
            }

            Button(
                onClick = {
                    if (currentTimeIndex + visibleTimeSlots < times.size) currentTimeIndex += visibleTimeSlots
                },
                enabled = currentTimeIndex + visibleTimeSlots < times.size
            ) {
                Text(text = ">")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0007AB))
            ) {
                Text(text = "ANTERIOR", color = Color.White)
            }

            Button(
                onClick = {
                    val clienteId = UserLoginSession.idCliente  // Defina o clienteId antes de chamar a navegação

                    // Verifique se o clienteId é válido
                    if (clienteId != null) {
                        selectedBarbeiroId?.let { barbeiroId ->
                            val dataSelecionada = LocalDate.of(LocalDate.now().year, selectedMonth, selectedDate)
                            val horaSelecionada = LocalDateTime.of(dataSelecionada, selectedTime ?: LocalTime.now())

                            Log.d("Agendamento", "Dados do Agendamento: barbeiroId=$barbeiroId, clienteId=$clienteId, servicoIds=[], inicio=$horaSelecionada")

                            // Chame a função para salvar o agendamento
                            viewModel.salvarAgendamento(
                                barbeiroId = barbeiroId,
                                clienteId = clienteId,
                                servicoIds = listOf(1),  // Adicione pelo menos um ID válido de serviço
                                inicio = horaSelecionada
                            )

                            // Navegue para a tela de busca de agendamento
                            navController.navigate("buscar-agendamento-cliente/$clienteId")
                        } ?: Log.e("Agendamento", "Por favor, selecione um barbeiro.")
                    } else {
                        Log.e("Agendamento", "Erro: clienteId não pode ser nulo!")
                        // Você pode mostrar uma mensagem para o usuário informando o erro
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0007AB))
            ) {
                Log.d("Agendamento", "idCliente no clique: ${UserLoginSession.idCliente}")
                Text(text = "CONCLUIR", color = Color.White)
            }

        }
    }

}

@Composable
fun BarberProfiles(imageUrl: String, onClick: () -> Unit) {
    Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDescription = "Foto do Barbeiro",
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() } // Chama a função onClick ao clicar
    )
}



fun generateTimeSlots(startHour: Int, endHour: Int): List<String> {
    val timeSlots = mutableListOf<String>()
    for (hour in startHour..endHour) {
        timeSlots.add("$hour:00")
        timeSlots.add("$hour:30")
    }
    return timeSlots
}

fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "JANEIRO"
        2 -> "FEVEREIRO"
        3 -> "MARÇO"
        4 -> "ABRIL"
        5 -> "MAIO"
        6 -> "JUNHO"
        7 -> "JULHO"
        8 -> "AGOSTO"
        9 -> "SETEMBRO"
        10 -> "OUTUBRO"
        11 -> "NOVEMBRO"
        12 -> "DEZEMBRO"
        else -> ""
    }
}

@Composable
fun Calendar(selectedDate: Int, selectedMonth: Int, onDateSelected: (Int) -> Unit) {
    val daysInMonth = YearMonth.of(LocalDate.now().year, selectedMonth).lengthOfMonth()
    val weekDays = listOf("D", "S", "T", "Q", "Q", "S", "S")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            weekDays.forEach { day ->
                Text(text = day, fontSize = 18.sp)
            }
        }

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
                                .clickable {
                                    onDateSelected(day)

                                },
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