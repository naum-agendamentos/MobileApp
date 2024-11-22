package com.example.mobile_app.visaobarbeiro.telas_mural

import MuralViewModel
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
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.visaobarbeiro.componentes.IconRow

enum class ButtonType2 {
    INFO, ALERT, URGENT
}

@Composable
fun MuralEdicao(
    id: Long,
    titulo: String,
    descricao: String,
    barbeiroId: Long,  // Novo parâmetro para o ID do barbeiro
    url: String,      // Novo parâmetro para a URL,
    navController: NavController,
    viewModel: MuralViewModel = viewModel()
) {
    val context = LocalContext.current // Obtenha o contexto aqui
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    var titulo by remember { mutableStateOf(titulo) }
    var descricao by remember { mutableStateOf(descricao) }
    var selectedButton by remember { mutableStateOf<ButtonType2?>(null) }

    viewModel.itemAtual = viewModel.itemAtual.copy(id = id, barbeiroId = barbeiroId, url = url)

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
                navBarb()
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
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.seta_retorno),
                            contentDescription = stringResource(R.string.imagem_retorno_desc), // Correção aqui
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp)
                                .clickable {
                                    // Voltar para a tela de listagem
                                    navController.navigate("muralListagem")
                                }
                        )

                        Text(
                            text = stringResource(R.string.editar_aviso), // Correção aqui
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(start = 35.dp)
                        )
                    }

                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = stringResource(R.string.titulo_label),
                            style = TextStyle(fontSize = 25.sp, color = Color.White)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = titulo,
                            onValueChange = { titulo = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(0.dp, Color.Transparent, RoundedCornerShape(0.dp))
                                .padding(bottom = 4.dp)
                        )

                        Text(
                            text = stringResource(R.string.descricao_label),
                            style = TextStyle(fontSize = 25.sp, color = Color.White)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = descricao,
                            onValueChange = { descricao = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(0.dp, Color.Transparent, RoundedCornerShape(0.dp))
                                .padding(bottom = 4.dp)
                        )

                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = stringResource(R.string.urgencia_label),
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White,
                            )
                        )

                        // Botões de urgência
                        Row {
                            Button(
                                onClick = {
                                    selectedButton = if (selectedButton == ButtonType2.INFO) null else ButtonType2.INFO
                                    viewModel.itemAtual = viewModel.itemAtual.copy(tipoAviso = "INFORMACAO")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType2.INFO)
                                        colorResource(id = R.color.btn_editar) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(0.5.dp, Color.White, RoundedCornerShape(12.dp))
                            ) {
                                Text(text = stringResource(R.string.info_button)) // Correção aqui
                            }

                            Button(
                                onClick = {
                                    selectedButton = if (selectedButton == ButtonType2.ALERT) null else ButtonType2.ALERT
                                    viewModel.itemAtual = viewModel.itemAtual.copy(tipoAviso = "INFORMACAO")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType2.ALERT)
                                        colorResource(id = R.color.btn_editar) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(0.5.dp, Color.White, RoundedCornerShape(12.dp))
                            ) {
                                Text(text = stringResource(R.string.alert_button)) // Correção aqui
                            }

                            Button(
                                onClick = {
                                    selectedButton = if (selectedButton == ButtonType2.URGENT) null else ButtonType2.URGENT
                                    viewModel.itemAtual = viewModel.itemAtual.copy(tipoAviso = "INFORMACAO")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType2.URGENT)
                                        colorResource(id = R.color.btn_editar) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(0.5.dp, Color.White, RoundedCornerShape(12.dp))
                            ) {
                                Text(text = stringResource(R.string.urgent_button)) // Correção aqui
                            }
                        }

                        // Botões para salvar
                        Spacer(modifier = Modifier.height(30.dp))

                        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                            Button(
                                onClick = {
                                    // Atualiza a itemAtual na ViewModel com os dados do usuário
                                    viewModel.itemAtual = viewModel.itemAtual.copy(titulo = titulo, descricao = descricao, barbeiroId = barbeiroId, url = url)
                                    viewModel.salvar()

                                    // Mostrar um Toast ao salvar
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.aviso_editado_sucesso), // Correção aqui
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Voltar para a tela anterior após salvar
                                    navController.popBackStack()
                                },
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.btn_editar),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(
                                    text = stringResource(R.string.editar_button),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    IconRow(navController = navController, activeIcon = R.drawable.pngalerta)
}

@Preview
@Composable
fun AvisoEditarPreview() {
    // Passar valores default para a pré-visualização
    val navController = rememberNavController() // Utilize um NavController fictício
    MuralEdicao(
        id = 1,
        titulo = "Título de Exemplo",
        descricao = "Descrição de Exemplo",
        url = "https://www.youtube.com/",
        barbeiroId = 1,
        navController = navController
    )
}
