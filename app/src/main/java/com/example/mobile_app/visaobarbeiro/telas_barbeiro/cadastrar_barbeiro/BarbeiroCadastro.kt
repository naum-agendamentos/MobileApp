import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarbeiroCad(viewModel: BarbeirosViewModel = viewModel(), navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    val context = LocalContext.current
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var uploadUrl by remember { mutableStateOf<String?>(null) }

    // Erros
    var erroNome by remember { mutableStateOf<String?>(null) }
    var erroEmail by remember { mutableStateOf<String?>(null) }
    var erroTelefone by remember { mutableStateOf<String?>(null) }
    var erroSenha by remember { mutableStateOf<String?>(null) }
    var erroConfSenha by remember { mutableStateOf<String?>(null) }
    var erroDescricao by remember { mutableStateOf<String?>(null) }


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
                    .height(584.dp)
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
                                text = "CADASTRAR BARBEIRO",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.White
                                )
                            )
                        }

                        ImagePicker(onImagePicked = { uri ->
                            selectedImageUri = uri
                            if (uri != null) {
                                viewModel.uploadImageToCloudinary(uri, context.contentResolver) { url ->
                                    uploadUrl = url
                                }
                            }
                        })

                        uploadUrl?.let {
                            Text(text = "Image URL: $it", color = Color.White)
                        }


                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = nome,
                            onValueChange = {
                                nome = it
                                erroNome = if (it.isEmpty()) "Nome não pode estar vazio" else null
                            },
                            label = { Text("Nome:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroNome != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroNome != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroNome?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                val regexEmail = Regex("^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
                                erroEmail = if (!it.matches(regexEmail)) "Insira um email válido" else null
                            },
                            label = { Text("E-mail:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroEmail != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroEmail != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroEmail?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = celular,
                            onValueChange = {
                                // Permite apenas números
                                if (it.all { char -> char.isDigit() } && it.length <= 11) {
                                    celular = it
                                    erroTelefone = if (it.length != 11) {
                                        "Insira um telefone válido"
                                    } else {
                                        null // Nenhum erro
                                    }
                                }
                            },
                            label = { Text("Celular:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroTelefone != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroTelefone != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroTelefone?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = senha,
                            onValueChange = {
                                senha = it
                                erroSenha = if (it.length < 6) "A senha deve conter no mínimo 6 dígitos" else null
                            },
                            label = { Text("Senha:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroSenha != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroSenha != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroSenha?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = confirmarSenha,
                            onValueChange = {
                                confirmarSenha = it
                                erroConfSenha = if (it != senha) "As senhas não coincidem" else null
                            },
                            label = { Text("Confirmar Senha:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroConfSenha != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroConfSenha != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroConfSenha?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = descricao,
                            onValueChange = {
                                descricao = it
                                erroDescricao = if (it.isEmpty()) "Descrição não pode estar vazia" else null
                            },
                            label = { Text("Descrição:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = if (erroDescricao != null) Color.Red else Color.White,
                                unfocusedBorderColor = if (erroDescricao != null) Color.Red else Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White)
                        )
                        erroDescricao?.let { Text(text = it, color = Color.Red) }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }

        Cadastrado(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            nome = nome,
            email = email,
            celular = celular,
            senha = senha,
            confirmarSenha = confirmarSenha,
            descricao = descricao,
            foto = uploadUrl ?: "https://img.freepik.com/fotos-gratis/homem-barbeiro-profissional-com-uma-tesoura-cabeleireiro-elegante-na-barbearia_167187-796.jpg?size=626&ext=jpg",
            viewModel = viewModel,
            navController = navController,
            isValid = nome.isNotEmpty() &&
                    email.isNotEmpty() &&
                    celular.isNotEmpty() &&
                    senha.isNotEmpty() &&
                    confirmarSenha.isNotEmpty() &&
                    descricao.isNotEmpty() &&
                    erroNome == null &&
                    erroEmail == null &&
                    erroTelefone == null &&
                    erroSenha == null &&
                    erroConfSenha == null &&
                    erroDescricao == null,
            onError = { errorMessage = it }
        )
    }

    IconRow(navController = navController, activeIcon = R.drawable.pngbarbeiros)
}

@Composable
fun Cadastrado(
    modifier: Modifier = Modifier,
    nome: String,
    email: String,
    celular: String,
    senha: String,
    confirmarSenha: String,
    descricao: String,
    foto: String,
    viewModel: BarbeirosViewModel,
    navController: NavController,
    isValid: Boolean,
    onError: (String) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(color = Color.Transparent)
            .padding(23.dp)
            .offset(y = -75.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.navigate("tela_barbeiros") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "CANCELAR", color = Color.White)
            }

            Button(
                onClick = {
                    if (!isValid) {
                        onError("Preencha todos os campos corretamente")
                        Toast.makeText(context, "Todos os campos são obrigatórios e devem ser válidos", Toast.LENGTH_SHORT).show()
                    } else if (senha != confirmarSenha) {
                        onError("As senhas não coincidem")
                    } else {
                        viewModel.cadastrarBarbeiro(
                            CadastrarBarbeiro(
                                nome = nome,
                                email = email,
                                telefone = celular,
                                senha = senha,
                                descricao = descricao,
                                foto = foto
                            )
                        )
                        Toast.makeText(context, "Barbeiro cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate("tela_barbeiros")
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
//fun BarbeiroCadPreview() {
//    BarbeiroCad()
//}
