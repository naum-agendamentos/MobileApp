package com.example.mobile_app.login

import LoginViewModel
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.example.mobile_app.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun Login(navController: NavHostController) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center, // Centraliza verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centraliza horizontalmente
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.barbearialogin), contentDescription = "Imagem Barbeiro",
                    modifier = Modifier.size(250.dp)
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Column {
                Text(text = "Email")
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp)
                        .border(0.dp, Color.Transparent)
                        .padding(0.dp),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,

                        ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Senha")
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp)
                        .border(0.dp, Color.Transparent)
                        .padding(0.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,

                        ),
                )
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = { logar(email, senha, navController) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0007AB)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(40.dp)
            ) {
                Text(
                    text = "ENTRAR",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ainda não tem conta?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Crie uma aqui",
                color = Color(0xFF0007AB),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("cadastrar_cliente")
                }
            )
        }
    }
}


fun logar(email: String?, senha: String?, navController: NavHostController) {
    val novaSession = NovaSession(email = email, senha = senha)
    val loginViewModel = LoginViewModel()
    GlobalScope.launch(Dispatchers.Main) {
        val session = loginViewModel.logar(novaSession)
        if (session != null && session.tipo == "CLIENTE") {
            navController.navigate("editarPerfil")
        }
        else if(session != null && session.tipo == "BARBEIRO"){
            navController.navigate("tela_inicial")
        }
    }
}
//
//@Preview(showSystemUi = true)
//@Composable
//fun LoginPreview(){
//    Login()
//}