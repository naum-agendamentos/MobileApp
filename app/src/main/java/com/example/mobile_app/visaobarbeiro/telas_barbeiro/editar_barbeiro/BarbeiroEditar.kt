import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.IconRow
import com.example.mobile_app.visaobarbeiro.navBarb
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.cadastrar_barbeiro.componente.CadastrarBarbeiro
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.editar_barbeiro.componente.EditarBarbeiro

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarbeiroEdit(viewModel: BarbeirosViewModel = viewModel(), navController: NavController, barbeiroId: Long?) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    val id = barbeiroId

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var uploadUrl by remember { mutableStateOf<String?>(null) }


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
                                text = "EDITAR BARBEIRO",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    color = Color.White
                                )
                            )
                        }

                        ImagePicker(onImagePicked = { uri -> selectedImageUri = uri })

                        selectedImageUri?.let {
                            Log.d("ImagePicker", "Selected image URI: $it")
                            CloudinaryUploader(imageUri = it, onUploadComplete = { url -> uploadUrl = url })
                        }


                        uploadUrl?.let {
                            Text(text = "Image URL: $it")
                        }



                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = nome,
                            onValueChange = { nome = it },
                            label = { Text("Nome:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.White
                            ),
                            textStyle = TextStyle(color = Color.White) // Definindo a cor do texto aqui
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("E-mail:", color = Color.White) },
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
                            value = celular,
                            onValueChange = {
                                if (it.length <= 11) { // Limita a 11 caracteres
                                    celular = it
                                }
                            },
                            label = { Text("Celular:", color = Color.White) },
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
                            value = senha,
                            onValueChange = { senha = it },
                            label = { Text("Senha:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.White
                            ),
                             textStyle = TextStyle(color = Color.White)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = confirmarSenha,
                            onValueChange = { confirmarSenha = it },
                            label = { Text("Confirmar Senha:", color = Color.White) },
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.White,
                                unfocusedBorderColor = Color.White,
                                cursorColor = Color.White
                            ),
                             textStyle = TextStyle(color = Color.White)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = descricao,
                            onValueChange = { descricao = it },
                            label = { Text("Descrição:", color = Color.White) },
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
        }

        if (id != null) {
            Editar(
                modifier = Modifier.align(Alignment.BottomCenter),
                barbeiroId = id,
                nome = nome,
                email = email,
                celular = celular,
                senha = senha,
                confirmarSenha = confirmarSenha,
                descricao = descricao,
                foto = uploadUrl ?: "https://img.freepik.com/fotos-gratis/homem-barbeiro-profissional-com-uma-tesoura-cabeleireiro-elegante-na-barbearia_167187-796.jpg?size=626&ext=jpg",
                viewModel = viewModel,
                navController = navController,
                onError = { errorMessage = it }
            )
        }
    }

    IconRow(activeIcon = R.drawable.pngbarbeiros)
}

@Composable
fun TitleBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        Button(
            onClick = { /* Adicione a ação desejada aqui */ },
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
            text = "EDITAR BARBEIRO",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.White
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(value: String, onValueChange: (String) -> Unit, label: String, isPassword: Boolean = false) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            cursorColor = Color.White
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun Editar(
    modifier: Modifier = Modifier,
    barbeiroId: Long,
    nome: String,
    email: String,
    celular: String,
    senha: String,
    confirmarSenha: String,
    descricao: String,
    foto: String,
    viewModel: BarbeirosViewModel,
    navController: NavController, // Adicione o NavController como parâmetro
    onError: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = Color.Transparent)
            .padding(23.dp)
            .offset(y = -90.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() }, // Ação de cancelar
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "CANCELAR", color = Color.White)
            }

            Button(
                onClick = {
                    // Validação dos campos obrigatórios
                    if (nome.isEmpty() || email.isEmpty() || celular.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty() || descricao.isEmpty()) {
                        onError("Todos os campos são obrigatórios")
                    } else if (senha != confirmarSenha) {
                        onError("As senhas não coincidem")
                    } else {
                        Log.d("Edit", "Chamando editarBarbeiro")
                        viewModel.editarBarbeiro(
                            barbeiroId = barbeiroId,
                            EditarBarbeiro(
                                nome = nome,
                                email = email,
                                telefone = celular,
                                senha = senha,
                                descricao = descricao,
                                foto = foto
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


@Preview(showBackground = true)
@Composable
fun EditPreview() {
    // Use a função rememberNavController para criar um NavController fictício
    val navController = rememberNavController()

    BarbeiroEdit(
        viewModel = BarbeirosViewModel(), // Crie uma instância do ViewModel
        navController = navController,
        barbeiroId = 1L // Use um ID fictício
    )
}