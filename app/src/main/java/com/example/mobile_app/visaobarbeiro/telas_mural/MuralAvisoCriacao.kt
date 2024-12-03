package com.example.mobile_app.visaobarbeiro.telas_mural

import MuralViewModel
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.mobile_app.visaobarbeiro.componentes.IconRow

enum class ButtonType {
    INFO, ALERT, URGENT
}

@Composable
fun MuralCriacao(navController: NavHostController, viewModel: MuralViewModel = viewModel(), context: Context) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    var showToast by remember { mutableStateOf(false) }

    var selectedButton by remember { mutableStateOf<ButtonType?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                navBarb(navController = navController)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(600.dp)
                    .background(
                        colorResource(id = R.color.preto),
                        RoundedCornerShape(12.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Column() {
                    Row(
                        modifier = Modifier
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.seta_retorno),
                            contentDescription = "Voltar",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp)
                                .clickable { navController.navigate("muralListagem") } // Navegar para MuralListagem
                        )

                        Text(
                            text = stringResource(R.string.title_activity_mural_criacao),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.White
                            ),
                            modifier = Modifier
                                .padding(start = 15.dp)
                        )
                    }

                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = stringResource(R.string.titulo_label),
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = viewModel.itemAtual.titulo ?: "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(0.dp, Color.Transparent, RoundedCornerShape(0.dp))
                                .padding(bottom = 4.dp),
                            onValueChange = { viewModel.itemAtual = viewModel.itemAtual.copy(titulo = it) }
                        )

                        Text(
                            text = stringResource(R.string.descricao_label),
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = viewModel.itemAtual.descricao ?: "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(0.dp, Color.Transparent, RoundedCornerShape(0.dp))
                                .padding(bottom = 4.dp),
                            onValueChange = { viewModel.itemAtual = viewModel.itemAtual.copy(descricao = it) }
                        )

                        viewModel.itemAtual = viewModel.itemAtual.copy(barbeiroId  = 1)
                        viewModel.itemAtual = viewModel.itemAtual.copy(url = "https://www.youtube.com/")

                        Text(
                            text = stringResource(R.string.urgencia_label),
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White
                            )
                        )

                        // Botões de urgência
                        Row {
                            Button(
                                onClick = {
                                    selectedButton =
                                        if (selectedButton == ButtonType.INFO) null else ButtonType.INFO
                                    viewModel.itemAtual = viewModel.itemAtual.copy(tipoAviso = "INFORMACAO")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType.INFO) colorResource(
                                        id = R.color.btn_editar
                                    ) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(
                                        width = 0.5.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            ) {
                                Text(text = stringResource(R.string.info_button))
                            }

                            Button(
                                onClick = {
                                    selectedButton =
                                        if (selectedButton == ButtonType.ALERT) null else ButtonType.ALERT
                                    viewModel.itemAtual = viewModel.itemAtual.copy(tipoAviso = "ALERTA")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType.ALERT) colorResource(
                                        id = R.color.btn_editar
                                    ) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(
                                        width = 0.5.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            ) {
                                Text(text = stringResource(R.string.alert_button))
                            }

                            Button(
                                onClick = {
                                    selectedButton =
                                        if (selectedButton == ButtonType.URGENT) null else ButtonType.URGENT
                                    viewModel.itemAtual = viewModel.itemAtual.copy(tipoAviso = "URGENTE")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType.URGENT) colorResource(
                                        id = R.color.btn_editar
                                    ) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(
                                        width = 0.5.dp,
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            ) {
                                Text(text = stringResource(R.string.urgent_button))
                            }
                        }

                        // Adicionar um espaço antes dos botões "Cancelar" e "Cadastrar"
                        Spacer(modifier = Modifier.height(30.dp))

                        // Botões de ação (Cancelar e Cadastrar)
                        Row(
                            modifier = Modifier.align(Alignment.CenterHorizontally) // Centraliza os botões
                        ) {
                            Button(
                                onClick = {
                                    viewModel.salvar()
                                    showToast = true // Sinaliza que o Toast deve ser exibido
                                    navController.navigate("muralListagem")
                                },
                                Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.btn_cadastrar),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(text = stringResource(R.string.cadastrar_button), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }

                            val cadastradoSucessoMessage = stringResource(R.string.cadastrado_sucesso)

                            LaunchedEffect(showToast) {
                                if (showToast) {
                                    Toast.makeText(context, cadastradoSucessoMessage, Toast.LENGTH_SHORT).show()
                                    showToast = false // Reseta o estado para evitar múltiplas exibições
                                }
                            }
                        }
                    }
                }
            }
        }
        IconRow(navController = navController, activeIcon = R.drawable.pngalerta)
    }
}

@Preview
@Composable
fun MuralAvisoPreview() {
    val context = LocalContext.current
    val navController = rememberNavController() // Cria um navController fictício
    MuralCriacao(navController = navController, context = context)
}