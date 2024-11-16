package com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobile_app.R
import kotlinx.coroutines.launch
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia.componente.SemanaEntity
import org.json.JSONException
import org.json.JSONObject
import java.net.URLDecoder
import kotlin.math.log
import kotlin.properties.Delegates

@Composable
fun telaBloqueioDeDia(navController: NavController, semanaJson: String?, id: String) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    val viewModel: BarbeirosViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val loadingState = remember { mutableStateOf(true) }
    val isInitialized = remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Parse JSON do barbeiro e inicializa o estado semana
    val semana = remember { mutableStateListOf(true, true, true, true, true, true, true) }
    LaunchedEffect(semanaJson) {
        if (!isInitialized.value && semanaJson != null) {
            try {
                val json = JSONObject(URLDecoder.decode(semanaJson, "UTF-8"))
                semana[0] = json.getBoolean("domingo")
                semana[1] = json.getBoolean("segunda")
                semana[2] = json.getBoolean("terca")
                semana[3] = json.getBoolean("quarta")
                semana[4] = json.getBoolean("quinta")
                semana[5] = json.getBoolean("sexta")
                semana[6] = json.getBoolean("sabado")
                isInitialized.value = true
            } catch (e: JSONException) {
                Log.e("JSON_ERROR", "Erro ao decodificar JSON: ${e.message}")
            }
        }
    }

    val diasDaSemana = listOf("Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado")

    Log.e("semana", semanaJson.toString())
    Log.e("barbeiro", id)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        navBarb()

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.bloquear_dia),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.TopCenter).padding(top = 90.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .height(430.dp)
                    .width(350.dp)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                itemsIndexed(diasDaSemana) { index, dia ->
                    Box(
                        modifier = Modifier
                            .width(170.dp)
                            .padding(vertical = 8.dp)
                            .background(
                                if (semana[index]) Color.Transparent else Color.Red,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(3.dp, Color.White, RoundedCornerShape(10.dp))
                            .padding(16.dp)
                            .clickable { semana[index] = !semana[index] },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = dia,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 20.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },  // ação de cancelamento
                    modifier = Modifier.width(160.dp).height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.btn_vermelho),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.cancelar_button),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                Button(
                    onClick = {
                        val semanaEntity = SemanaEntity(
                            domingo = semana[0],
                            segunda = semana[1],
                            terca = semana[2],
                            quarta = semana[3],
                            quinta = semana[4],
                            sexta = semana[5],
                            sabado = semana[6]
                        )

                        coroutineScope.launch {
                            try {
                                viewModel.atualizarSemanaBarbeiro(
                                    id.toLong(),
                                    semanaEntity
                                )
                                Toast.makeText(
                                    context,
                                    "Semana salva com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("menu-barbeiro-bloqueio")
                            } catch (e: Exception) {
                                Log.e("API_ERROR", "Erro ao salvar semana", e)
                            }
                        }
                    },
                    modifier = Modifier.width(160.dp).height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.btn_cadastrar),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.salvar_button),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
    }

}
