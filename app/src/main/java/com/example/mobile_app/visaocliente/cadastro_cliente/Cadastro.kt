package com.example.mobile_app.visaocliente.cadastro_cliente

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            onValueChange = { nome = it },
            label = { Text(text = "Nome") },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        fun phoneVisualTransformation(): VisualTransformation {
            return VisualTransformation { text ->
                val trimmed = text.text.take(11)
                val output = StringBuilder()
                for (i in trimmed.indices) {
                    output.append(trimmed[i])
                    when (i) {
                        1 -> output.append(" ")
                        6 -> output.append("-")
                    }
                }
                TransformedText(AnnotatedString(output.toString()), OffsetMapping.Identity)
            }
        }

        OutlinedTextField(
            value = telefone,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    telefone = it.take(11)
                }
            },
            label = { Text(text = "Telefone") },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = phoneVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text(text = "Password") },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = confirmarSenha,
            onValueChange = { confirmarSenha = it },
            label = { Text(text = "Confirmar senha") },
            modifier = Modifier
                .width(280.dp)
                .height(56.dp)
                .background(Color.White, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                    message = "Preencha todos os campos"
                    showMessage = true
                } else if (senha != confirmarSenha) {
                    message = "As senhas não coincidem"
                    showMessage = true
                } else {
                    viewModel.cadastrarCliente(
                        Cliente(
                            nome = nome,
                            email = email,
                            telefone = telefone,
                            senha = senha
                        ),
                        onSuccess = {
                            message = "Cliente cadastrado com sucesso"
                            showMessage = true
                            navController.navigate("")
                        },
                        onError = { errorMessage ->
                            message = errorMessage
                            showMessage = true
                        }
                    )
                }
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .width(150.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0007AB))
        ) {
            Text(
                text = "Cadastrar",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            )
        }


        Spacer(modifier = Modifier.height(30.dp))

        Text(text = "Já tem uma conta?")
        Text(
            text = "Entre aqui",
            style = TextStyle(
                color = Color(0xFF0000FF)
            ),
            modifier = Modifier.clickable {
                navController.navigate("login_screen")
            }
        )
    }

    if (showMessage) {
        Snackbar(
            action = {
                TextButton(onClick = { showMessage = false }) {
                    Text("OK", color = Color.White)
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = message)
        }
    }

}
