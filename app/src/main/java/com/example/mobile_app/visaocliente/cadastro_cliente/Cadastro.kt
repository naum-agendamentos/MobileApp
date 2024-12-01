package com.example.mobile_app.visaocliente.cadastro_cliente

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.visaocliente.telas_agendamento.agendamento_datahora.Cliente
import java.lang.StringBuilder
import kotlin.text.all
import kotlin.text.indices
import kotlin.text.isDigit
import kotlin.text.take

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Cadastro(
    viewModel: CadastroClienteViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    var erroNome by remember { mutableStateOf<String?>(null) }
    var erroEmail by remember { mutableStateOf<String?>(null) }
    var erroTelefone by remember { mutableStateOf<String?>(null) }
    var erroSenha by remember { mutableStateOf<String?>(null) }
    var erroConfSenha by remember { mutableStateOf<String?>(null) }

    var showMessage by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logobarbeiro),
            contentDescription = "Login image",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 40.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "BARBEARIA",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = nome,
            onValueChange = {
                nome = it
                erroNome = if (it.isEmpty()) "Nome não pode estar vazio" else null
            },
            label = { Text("Nome:", color = Color.Black) },
            modifier = Modifier
                .width(280.dp) ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (erroNome != null) Color.Red else Color.Black,
                unfocusedBorderColor = if (erroNome != null) Color.Red else Color.Black,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black)
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
            label = { Text("E-mail:", color = Color.Black) },
            modifier = Modifier
                .width(280.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (erroEmail != null) Color.Red else Color.Black,
                unfocusedBorderColor = if (erroEmail != null) Color.Red else Color.Black,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black)
        )
        erroEmail?.let { Text(text = it, color = Color.Red) }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = telefone,
            onValueChange = {
                // Permite apenas números
                if (it.all { char -> char.isDigit() } && it.length <= 11) {
                    telefone = it
                    erroTelefone = if (it.length != 11) {
                        "Insira um telefone válido"
                    } else {
                        null // Nenhum erro
                    }
                }
            },
            label = { Text("Celular:", color = Color.Black) },
            modifier = Modifier
                .width(280.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (erroTelefone != null) Color.Red else Color.Black,
                unfocusedBorderColor = if (erroTelefone != null) Color.Red else Color.Black,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black)
        )
        erroTelefone?.let { Text(text = it, color = Color.Red) }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = {
                senha = it
                erroSenha = if (it.length < 6) "A senha deve conter no mínimo 6 dígitos" else null
            },
            label = { Text("Senha:", color = Color.Black) },
            modifier = Modifier
                .width(280.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (erroSenha != null) Color.Red else Color.Black,
                unfocusedBorderColor = if (erroSenha != null) Color.Red else Color.Black,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black)
        )
        erroSenha?.let { Text(text = it, color = Color.Red) }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = confirmarSenha,
            onValueChange = {
                confirmarSenha = it
                erroConfSenha = if (it != senha) "As senhas não coincidem" else null
            },
            label = { Text("Confirmar Senha:", color = Color.Black) },
            modifier = Modifier
                .width(280.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (erroConfSenha != null) Color.Red else Color.Black,
                unfocusedBorderColor = if (erroConfSenha != null) Color.Red else Color.Black,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black)
        )
        erroConfSenha?.let { Text(text = it, color = Color.Red) }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                    showMessage = true
                    message = "Todos os campos são obrigatórios"
                } else if (senha != confirmarSenha) {
                    showMessage = true
                    message = "As senhas não coincidem"
                } else {
                    val cliente = Cliente(
                        id = 0, // ou qualquer valor apropriado
                        nome = nome,
                        email = email,
                        telefone = telefone,
                        senha = senha
                    )
                    viewModel.cadastrarCliente(cliente,
                        onSuccess = {
                            showMessage = true
                            message = "Cliente cadastrado com sucesso!"
                            navController.navigate("Login") // Ajuste para a rota correta
                        },
                        onError = {
                            showMessage = true
                            message = it
                        }
                    )
                }
            },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp)
                .background(Color(0xFFFF9800), shape = RoundedCornerShape(12.dp)) // Gradiente ou cor sólida
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)), // Adicionando sombra para dar profundidade
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0007AB))
        ) {
            Text(text = "Cadastrar", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Já tem conta?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "faça login aqui",
            color = Color(0xFF0007AB),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("login")
            }
        )

        if (showMessage) {
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
            showMessage = false
        }
    }
}


