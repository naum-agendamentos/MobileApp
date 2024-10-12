package com.example.mobile_app.visaocliente.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobile_app.R
import com.example.mobile_app.visaocliente.pages.MeuPerfil

@Composable
fun FundoTela(content: @Composable () -> Unit){
    Image(
        painter = painterResource(id = R.drawable.fundo_cliente),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column  {
        Header()
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.91f),
            contentAlignment = Alignment.Center){
            content()
        }
        Navigation()
    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FundoTelaPreview(){
    FundoTela{
        MeuPerfil()
    }
}