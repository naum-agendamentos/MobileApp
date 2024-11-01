//package com.example.mobile_app.visaocliente.componentes.meuperfilcomponentes
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.mobile_app.visaocliente.ui.theme.Gray
//import com.example.mobile_app.visaocliente.ui.theme.Red
//
//@Composable
//fun InputsTrocarSenha() {
//    // Inicializa as variáveis com os valores dos dados do cliente ou com o texto "Carregando..." enquanto carrega
//    var senhaAtual by remember { mutableStateOf("") }
//    var novaSenha by remember { mutableStateOf("") }
//    var confirmacao by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier.fillMaxHeight(0.6f),
//        verticalArrangement = Arrangement.SpaceAround,
//    ) {
//        Column(
//            modifier = Modifier
//                .height(60.dp)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.SpaceAround,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Senha Atual:",
//                fontSize = 20.sp,
//                modifier = Modifier.fillMaxWidth(0.9f),
//                fontWeight = FontWeight.Bold
//            )
//            Surface(
//                modifier = Modifier.fillMaxWidth(0.9f),
//                shape = RoundedCornerShape(15.dp),
//                border = BorderStroke(1.dp, Gray)
//            ) {
//                OutlinedTextField(
//                    value = senhaAtual,
//                    onValueChange = { senhaAtual = it },
//                    modifier = Modifier.fillMaxWidth(0.9f)
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier
//                .height(60.dp)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.SpaceAround,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Nova Senha:",
//                fontSize = 20.sp,
//                modifier = Modifier.fillMaxWidth(0.9f),
//                fontWeight = FontWeight.Bold
//            )
//            Surface(
//                modifier = Modifier.fillMaxWidth(0.9f),
//                shape = RoundedCornerShape(15.dp),
//                border = BorderStroke(1.dp, Gray)
//            ) {
//                OutlinedTextField(
//                    value = novaSenha,
//                    onValueChange = { novaSenha = it },
//                    modifier = Modifier.fillMaxWidth(0.9f)
//                )
//            }
//        }
//
//        Column(
//            modifier = Modifier
//                .height(60.dp)
//                .fillMaxWidth(),
//            verticalArrangement = Arrangement.SpaceAround,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Confirme a Senha:",
//                fontSize = 20.sp,
//                modifier = Modifier.fillMaxWidth(0.9f),
//                fontWeight = FontWeight.Bold
//            )
//            Surface(
//                modifier = Modifier.fillMaxWidth(0.9f),
//                shape = RoundedCornerShape(15.dp),
//                border = BorderStroke(1.dp, Gray)
//            ) {
//                OutlinedTextField(
//                    value = confirmacao,
//                    onValueChange = { confirmacao = it },
//                    modifier = Modifier.fillMaxWidth(0.9f)
//                )
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun inputsTrocarSenhaPreview() {
//    InputsTrocarSenha()
//}
//}