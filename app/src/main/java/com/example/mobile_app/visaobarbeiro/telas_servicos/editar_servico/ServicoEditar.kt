import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_servicos.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servicos.cadastrar_servico.componente.CadastrarServico
import com.example.mobile_app.visaobarbeiro.telas_servicos.editar_servico.componente.EditarServico

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditServico(viewModel: ServicosViewModel = viewModel(), navController: NavController, servicoId: Long?) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    val id = servicoId

    var nomeServico by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var tempoServico by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

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

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(400.dp)
                    .offset(y = -50.dp)
                    .background(Color(0x0FFFFFFF), shape = RoundedCornerShape(15.dp))
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Button(
                                onClick = { navController.popBackStack() },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(Color.Transparent),
                                contentPadding = PaddingValues(0.dp),
                                modifier = Modifier
                                    .size(30.dp)
                                    .border(3.dp, Color.White, CircleShape)
                            ) {
                                Text(
                                    text = "<",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = Color.White
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "CADASTRAR SERVIÇO",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.White
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = nomeServico,
                            onValueChange = { nomeServico = it },
                            label = { Text("Nome:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = preco,
                            onValueChange = { preco = it },
                            label = { Text("Preço: R$", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = tempoServico,
                            onValueChange = {
                                if (it.length <= 5) {
                                    tempoServico = it
                                }
                            },
                            label = { Text("Tempo: min", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )


                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            if (id != null) {
            EditarServ(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                servicoId = id,
                nomeServico = nomeServico,
                preco = preco,
                tempoServico = tempoServico,
                viewModel = viewModel,
                navController = navController,
                onError = { errorMessage = it }
            )
            }
        }
    }
    IconRow(navController = navController, activeIcon = R.drawable.pngtesoura)
}

@Composable
fun EditarServ(
    modifier: Modifier = Modifier,
    servicoId: Long,
    nomeServico: String,
    preco: String,
    tempoServico: String,
    viewModel: ServicosViewModel,
    navController: NavController,
    onError: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.Transparent)
            .padding(23.dp)
            .offset(y = -280.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {  navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "CANCELAR", color = Color.White)
            }

            Button(
                onClick = {
                    // Validação dos campos obrigatórios
                    if (nomeServico.isEmpty() || preco.isEmpty() || tempoServico.isEmpty()) {
                        onError("Todos os campos são obrigatórios")
                    } else {

                        val precoDouble = preco.toDouble()
                        val tempoServicoInt = tempoServico.toInt()

                        viewModel.editarServico(
                            servicoId = servicoId,
                            EditarServico(
                                nomeServico = nomeServico,
                                preco = precoDouble,
                                tempoServico = tempoServicoInt,

                                )
                        )
                        navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303495)),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "EDITAR", color = Color.White)
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewCadastroServico() {
//    CadastroServico()
//}