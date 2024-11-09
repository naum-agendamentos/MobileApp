package com.example.mobile_app.visaocliente.telas_editarPerfil.componentes_editarPerfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R

@Composable
fun DadosAtualizadosSucesso(){
    Column(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "DADOS ATUALIZADOS\nCOM SUCESSO!", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center)
        Image(
            painter = painterResource(id = R.drawable.check2),
            contentDescription = "Ícone de verificação",
            modifier = Modifier
                .size(89.dp)
        )
    }
}

@Composable
fun DadosExcluidosSucesso(){
    Column(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "DADOS EXCLUÍDOS\nCOM SUCESSO!", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center)
        Image(
            painter = painterResource(id = R.drawable.check2),
            contentDescription = "Ícone de verificação",
            modifier = Modifier
                .size(89.dp)
        )
    }
}



@Composable
fun ExcluirContaDecisao(){
    Column(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Tem certeza que\n" +
                "deseja excluir seu\n"  +
                " perfil? Esta ação \n " +
                "não poderá ser \ndesfeita", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center, style = TextStyle(lineHeight = 50.sp)
        )
    }
}

@Composable
fun ExcluirContaDecisao2(){
    Column(
        modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Tem certeza que\n" +
                "deseja excluir\n"  +
                " esse agendamento\n ", fontWeight = FontWeight.Bold, fontSize = 30.sp, textAlign = TextAlign.Center, style = TextStyle(lineHeight = 50.sp)
        )
    }
}