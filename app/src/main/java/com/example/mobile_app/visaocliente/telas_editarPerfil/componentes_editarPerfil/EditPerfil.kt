package com.example.mobile_app.visaocliente.telas_editarPerfil.componentes_editarPerfil

import EditPerfilViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mobile_app.login.UserLoginSession
import com.example.mobile_app.visaocliente.telas_editarPerfil.DadosCliente

import com.example.mobile_app.visaocliente.ui.theme.Blue
import com.example.mobile_app.visaocliente.ui.theme.Gray
import com.example.mobile_app.visaocliente.ui.theme.Green
import com.example.mobile_app.visaocliente.ui.theme.Red

@Composable
fun EditPerfil(viewModel: EditPerfilViewModel = viewModel(), navController: NavController){

    val dadosCliente = remember { mutableStateOf(viewModel.dadosCliente) }
    var idCliente by remember { mutableStateOf<Long?>(null) }
    var nomeState by remember { mutableStateOf(viewModel.dadosCliente.value?.nome ?: "Carregando...") }
    var emailState by remember { mutableStateOf(viewModel.dadosCliente.value?.email ?: "Carregando...") }
    var telefoneState by remember { mutableStateOf(viewModel.dadosCliente.value?.telefone ?: "Carregando...") }

    var senhaState by remember { mutableStateOf(UserLoginSession.senha ?: "") }


    LaunchedEffect(viewModel.dadosCliente.value) {
        viewModel.dadosCliente.value?.let {
            idCliente = it.id ?: null
            nomeState = it.nome ?: "Nome indisponível"
            emailState = it.email ?: "Email indisponível"
            telefoneState = it.telefone ?: "Telefone indisponível"
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
    ){

        //variaveis de controle
        var btnsEdit by remember { mutableStateOf(false) }
        var nomeClick by remember { mutableStateOf(false) }
        var emailClick by remember { mutableStateOf(false) }
        var telefoneClick by remember { mutableStateOf(false) }
        var dadosAtualizados by remember { mutableStateOf(false) }
        var excluirConta by remember { mutableStateOf(false) }
        var contaExcluida by remember { mutableStateOf(false) }
        val showFirstColumn = remember { mutableStateOf(true) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ){
            Text(
                text = if (showFirstColumn.value) "MEU PERFIL" else "EDITAR SENHA",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if(dadosAtualizados){
            DadosAtualizadosSucesso()
        }
        else if(excluirConta){
            ExcluirContaDecisao()

        }
        else if(contaExcluida){
            DadosExcluidosSucesso()
        }

        else{
            AnimatedVisibility(
                visible = showFirstColumn.value,
                enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()
            ) {
                //inputs dos dados usuario
                Column(
                    modifier = Modifier.fillMaxHeight(0.6f),
                    verticalArrangement = Arrangement.SpaceAround,
                ){

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        var nomeSufixe by remember { mutableStateOf("Nome:") }


                        var combinedTextName = if (nomeClick) nomeState else "$nomeSufixe $nomeState"

                        OutlinedTextField(
                            value = combinedTextName,
                            onValueChange = { newText ->
                                // Mantém o texto digitado corretamente, incluindo espaços
                                nomeState = newText.removePrefix(nomeSufixe).trimStart() // Remove apenas espaços no início
                                combinedTextName = "$nomeSufixe $nomeState" // Atualiza o texto combinado
                            },
                            label = {
                                Text(
                                    "Nome:",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = if (!nomeClick) Color.Transparent else Color.Black
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(60.dp)
                                .padding(0.dp),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = if (!nomeClick) Color.Transparent else Color.Black,
                                unfocusedBorderColor = if (!nomeClick) Color.Transparent else Color.Black,
                                disabledBorderColor = Color.Transparent,
                                errorBorderColor = Color.Transparent,
                                disabledTextColor = Color.Gray,
                            ),
                            enabled = nomeClick,
                        )



                        if(btnsEdit){
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(30.dp)
                                    .fillMaxHeight()
                                    .clickable {
                                        nomeClick = true
                                    }
                            )
                        }
                    }


                    var emailSufixe by remember { mutableStateOf("Email:") }


                    var combinedTextEmail = if (emailClick) emailState else "$emailSufixe $emailState"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedTextField(
                            value = combinedTextEmail,
                            label = {
                                Text(
                                    "Email:",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = if (!emailClick) Color.Transparent else Color.Black
                                    )
                                )
                            },
                            onValueChange = { newText ->
                                // Ajusta o texto removendo apenas o prefixo e mantendo os espaços internos
                                emailState = newText.removePrefix(emailSufixe).trimStart() // Remove apenas espaços no início
                                combinedTextEmail = "$emailSufixe$emailState" // Atualiza o texto combinado
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(60.dp)
                                .padding(0.dp),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = if (!emailClick) Color.Transparent else Color.Black,
                                unfocusedBorderColor = if (!emailClick) Color.Transparent else Color.Black,
                                disabledBorderColor = Color.Transparent, // Transparente quando desabilitado
                                errorBorderColor = Color.Transparent, // Transparente em erro
                                disabledTextColor = Color.Gray // Opcional: cor do texto quando desabilitado
                            ),
                            enabled = emailClick // Desabilita o campo quando emailClick for falso
                        )

                        if(btnsEdit){
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(30.dp)
                                    .fillMaxHeight()
                                    .clickable {
                                        emailClick = true
                                    }

                            )
                        }
                    }

                    var telefoneSufixe by remember { mutableStateOf("Telefone:") }


                    var combinedTextTelefone = if (telefoneClick) telefoneState else "$telefoneSufixe $telefoneState"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedTextField(
                            value = combinedTextTelefone,
                            label = {
                                Text(
                                    "Telefone:",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = if (!telefoneClick) Color.Transparent else Color.Black
                                    )
                                )
                            },
                            onValueChange = { newText ->
                                // Ajusta o texto, removendo apenas o prefixo e preservando os espaços internos
                                telefoneState = newText.removePrefix(telefoneSufixe).trimStart() // Remove apenas espaços no início
                                combinedTextTelefone = "$telefoneSufixe$telefoneState" // Atualiza o texto combinado
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(60.dp)
                                .padding(0.dp),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = if (!telefoneClick) Color.Transparent else Color.Black,
                                unfocusedBorderColor = if (!telefoneClick) Color.Transparent else Color.Black,
                                disabledBorderColor = Color.Transparent, // Transparente em estado desabilitado
                                errorBorderColor = Color.Transparent, // Transparente em estado de erro
                                disabledTextColor = Color.Gray // Opcional: cor do texto desabilitado
                            ),
                            enabled = telefoneClick // Desabilita o campo quando telefoneClick for falso
                        )


                        if(btnsEdit){
                            Icon(
                                imageVector = Icons.Filled.Edit,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(30.dp)
                                    .fillMaxHeight()
                                    .clickable {
                                        telefoneClick = true
                                    }

                            )
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = !showFirstColumn.value,
                enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
                exit = slideOutHorizontally(targetOffsetX = { -it }) + fadeOut()
            ) {
                InputsTrocarSenha()
            }




        }


        //Mudar Senha/Excluir Conta
        if(btnsEdit && !excluirConta && showFirstColumn.value){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically

            ){
                Text(text = "Mudar Senha", fontWeight = FontWeight.Bold, fontSize = 16.sp,modifier = Modifier.clickable {showFirstColumn.value = !showFirstColumn.value})
                Text(text = "Excluir Conta", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Red, modifier = Modifier.clickable {
                    excluirConta = !excluirConta
                })
            }
        }

        //Botões azuis
        if(!btnsEdit && !excluirConta){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                contentAlignment = Alignment.BottomCenter
            ){
                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){


                    //Habilitar Edição
                    if(!dadosAtualizados && !contaExcluida){
                        Button(
                            onClick = {btnsEdit = !btnsEdit},
                            modifier = Modifier
                                .width(155.dp)
                                .height(40.dp)
                                .background(Blue),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Blue,
                                contentColor = Color.White
                            )
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(30.dp)
                                        .fillMaxHeight()

                                )
                                Text(
                                    fontSize = 20.sp,
                                    text = "EDITAR",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    //Botão "OK" Após Exclusão
                    else if(contaExcluida){
                        Button(
                            onClick = {
                                contaExcluida = !contaExcluida
                                navController.navigate("cliente-excluido")
                            },
                            modifier = Modifier
                                .width(155.dp)
                                .height(40.dp)
                                .background(Blue),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Blue,
                                contentColor = Color.White
                            )
                        ) {
                            Row {
                                Text(
                                    fontSize = 20.sp,
                                    text = "OK",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    //Botão "OK" Após Edição
                    else{
                        Button(
                            onClick = {dadosAtualizados = !dadosAtualizados
                                navController.navigate("cliente-editado")},
                            modifier = Modifier
                                .width(155.dp)
                                .height(40.dp)
                                .background(Blue),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Blue,
                                contentColor = Color.White
                            )
                        ) {
                            Row {
                                Text(
                                    fontSize = 20.sp,
                                    text = "OK",
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

        //Após Clicar em excluir conta
        else if(excluirConta){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ){
                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){
                    Button(
                        onClick = {
                            excluirConta = !excluirConta
                        },
                        modifier = Modifier
                            .width(155.dp)
                            .height(40.dp)
                            .background(Gray),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Row {
                            Text(
                                fontSize = 20.sp,
                                text = "CANCELAR",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){
                    Button(
                        onClick = {
                            viewModel.excluirCliente {
                                // Ação de sucesso após a exclusão
                                btnsEdit = !btnsEdit
                                contaExcluida = !contaExcluida
                                excluirConta = !excluirConta
                            }

                        },
                        modifier = Modifier
                            .width(155.dp)
                            .height(40.dp)
                            .background(Red),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red,
                            contentColor = Color.White
                        )
                    ) {
                        Row {
                            Text(
                                fontSize = 20.sp,
                                text = "EXCLUIR",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        else if(!showFirstColumn.value){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ){
                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){
                    Button(
                        onClick = {
                            btnsEdit = !btnsEdit
                            nomeClick = false
                            emailClick = false
                            telefoneClick = false
                            showFirstColumn.value = !showFirstColumn.value
                        },
                        modifier = Modifier
                            .width(155.dp)
                            .height(40.dp)
                            .background(Gray),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Row {
                            Text(
                                fontSize = 20.sp,
                                text = "Cancelar",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){
                    Button(
                        onClick = {
                            dadosAtualizados = !dadosAtualizados
                            btnsEdit = !btnsEdit

                        },
                        modifier = Modifier
                            .width(155.dp)
                            .height(40.dp)
                            .background(Green),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green,
                            contentColor = Color.White
                        )
                    ) {
                        Row {
                            Text(
                                fontSize = 20.sp,
                                text = "SALVAR1",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
        //Após Habilitar Edição
        else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
            ){
                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){
                    Button(
                        onClick = {
                            btnsEdit = !btnsEdit
                            nomeClick = false
                            emailClick = false
                            telefoneClick = false
                        },
                        modifier = Modifier
                            .width(155.dp)
                            .height(40.dp)
                            .background(Gray),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Gray,
                            contentColor = Color.White
                        )
                    ) {
                        Row {
                            Text(
                                fontSize = 20.sp,
                                text = "Cancelar",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Surface(
                    shape = RoundedCornerShape(5.dp)
                ){
                    Button(
                        onClick = {
//                            dadosAtualizados = !dadosAtualizados
//                            btnsEdit = !btnsEdit
                            viewModel.updateDadosCliente(DadosCliente(idCliente, nomeState, emailState, senhaState, telefoneState)) {
                                // Executa essas alterações de estado apenas após o sucesso
                                dadosAtualizados = !dadosAtualizados
                                btnsEdit = !btnsEdit
                            }
                        },
                        modifier = Modifier
                            .width(155.dp)
                            .height(40.dp)
                            .background(Green),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Green,
                            contentColor = Color.White
                        )
                    ) {
                        Row {
                            Text(
                                fontSize = 20.sp,
                                text = "SALVAR",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}


