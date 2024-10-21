package com.example.mobile_app.visaobarbeiro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ScheduleScreen() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM")
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    val nomebarb = "Bryan Liaris"
    navBarb()

    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        navBarb()

        androidx.compose.material3.Text(
            text = "AGENDAMENTOS",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = -10.dp)
                .width(350.dp)
                .height(550.dp)
                .background(Color(0x0FFFFFFF), shape = RoundedCornerShape(15.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

                Box(modifier = Modifier
                    .width(336.dp)
                    .height(58.dp)

                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {


                        Box(
                            modifier = Modifier
                                .width(29.dp)
                                .height(29.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                                .align(Alignment.CenterVertically)
                        ) {
                            Button(
                                onClick = { selectedDate = selectedDate.plusDays(1) },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = "<",
                                    color = Color.Black,

                                    )
                            }
                        }

                    Column {
                        Text(
                            text = nomebarb,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = selectedDate.format(dateFormatter),
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }



                        Box(
                            modifier = Modifier
                                .width(29.dp)
                                .height(29.dp)
                                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                                .align(Alignment.CenterVertically)
                        ) {
                            Button(
                                onClick = { selectedDate = selectedDate.plusDays(1) },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = ">",
                                    color = Color.Black,

                                )
                            }
                        }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val appointments = listOf(
                "10:00 Bryan Liaris corte simples",
                "10:30 Bryan Liaris corte simples",
                "11:00 Bryan Liaris corte simples",
                "11:30 Bryan Liaris corte simples"
            )

            appointments.forEach { appointment ->
                val parts = appointment.split(" ", limit = 2)
                val time = parts[0]
                val details = parts.getOrElse(1) { "" }

                Card(
                    shape = RoundedCornerShape(15.dp),
                    backgroundColor = Color.DarkGray,
                    modifier = Modifier
                        .width(318.dp)
                        .height(72.dp)
                        .padding(vertical = 2.9.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(15.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .border(1.dp, Color.White, RoundedCornerShape(5.dp))
                                .padding(8.dp)
                        ) {
                            Text(
                                text = time,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = details,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
        }

    }

  //  IconRow()

}

@Preview(showBackground = false)
@Composable
fun agendamentos() {
    ScheduleScreen()
}
