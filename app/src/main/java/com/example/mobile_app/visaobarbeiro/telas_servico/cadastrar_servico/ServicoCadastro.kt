import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_servico.ServicosViewModel
import com.example.mobile_app.visaobarbeiro.telas_servico.cadastrar_servico.componente.CadastrarServico

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroServico(viewModel: ServicosViewModel = viewModel(), navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    var nomeServico by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var tempoServico by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var erroNomeServico by remember { mutableStateOf<String?>(null) }
    var erroPreco by remember { mutableStateOf<String?>(null) }
    var erroTempoServico by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        navBarb(navController = navController)

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
                            onValueChange = {
                                nomeServico = it
                                erroNomeServico = if (it.isEmpty()) "Nome não pode estar vazio" else null
                            },
                            label = { Text("Nome:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroNomeServico != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroNomeServico != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroNomeServico?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = preco,
                            onValueChange = { input ->
                                // Substitui a vírgula por ponto e permite apenas dígitos e um único ponto
                                val sanitizedInput = input.replace(',', '.')

                                if (sanitizedInput.all { char -> char.isDigit() || char == '.' }) {
                                    preco = sanitizedInput

                                    // Validação de preço: Verifica se o formato está correto
                                    erroPreco = when {
                                        sanitizedInput.isEmpty() -> "O preço não pode estar vazio." // Campo vazio
                                        sanitizedInput.count { it == '.' } > 1 -> "Insira um preço válido" // Mais de um ponto
                                        sanitizedInput.last() == '.' -> null // Nenhum erro
                                        else -> null // Nenhum erro se o formato for válido
                                    }
                                }
                            },
                            label = { Text("Preço: R$", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroPreco != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroPreco != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )

                        erroPreco?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))




                        OutlinedTextField(
                            value = tempoServico,
                            onValueChange = { input ->
                                // Atualiza o campo tempoServico com a nova entrada
                                tempoServico = input

                                // Validação para apenas permitir "30", "60", "90" ou deixar vazio
                                erroTempoServico = when {
                                    input.isEmpty() -> null // Permite campo vazio
                                    input in listOf("30", "60", "90") -> null // Permite valores válidos
                                    else -> "Insira um tempo válido (30, 60 ou 90)" // Mensagem de erro para valores inválidos
                                }
                            },
                            label = { Text("Tempo: 30, 60, 90", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroTempoServico != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroTempoServico != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )


                        erroTempoServico?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            CadastradoServ(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                nomeServico = nomeServico,
                preco = preco,
                tempoServico = tempoServico,
                viewModel = viewModel,
                navController = navController,
                isValid = nomeServico.isNotEmpty() &&
                        preco.isNotEmpty() &&
                        tempoServico.isNotEmpty() &&
                        erroNomeServico == null &&
                        erroPreco == null &&
                        erroTempoServico == null,
                onError = { errorMessage = it }
            )
        }
    }
    IconRow(navController = navController, activeIcon = R.drawable.pngtesoura)
}

@Composable
fun CadastradoServ(
    modifier: Modifier = Modifier,
    nomeServico: String,
    preco: String,
    tempoServico: String,
    viewModel: ServicosViewModel,
    navController: NavController,
    isValid: Boolean,
    onError: (String) -> Unit
) {
    val context = LocalContext.current
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
                    if (!isValid) {
                        onError("Preencha todos os campos corretamente")
                        Toast.makeText(
                            context,
                            "Todos os campos são obrigatórios e devem ser válidos",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        val precoDouble = preco.toDouble()
                        val tempoServicoInt = tempoServico.toInt()
                        val idBarbearia = 1L

                        viewModel.cadastrarServico(
                            CadastrarServico(
                                nomeServico = nomeServico,
                                preco = precoDouble,
                                tempoServico = tempoServicoInt,

                            ),
                            idBarbearia
                        )
                        navController.navigate("tela_servicos")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "SALVAR", color = Color.White)
            }
        }
    }
}

//@Preview
//@Composable
//fun PreviewCadastroServico() {
//    CadastroServico()
//}