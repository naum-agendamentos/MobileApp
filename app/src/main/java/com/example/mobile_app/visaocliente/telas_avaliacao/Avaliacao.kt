package com.example.mobile_app.visaocliente.telas_avaliacao

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.ui.theme.mobile_appTheme
import com.example.mobile_app.visaobarbeiro.NavCliente
import com.example.mobile_app.visaocliente.componentes.Header
import com.example.mobile_app.visaocliente.componentes.IconRowClient

@Composable
fun Avaliacao(navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_cliente)
    var estrelasSelecionadas by remember { mutableStateOf(5) }
    val viewModel: AvaliacaoViewModel = viewModel()
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Header(navController = navController)

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 90.dp, start = 74.dp, end = 74.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = "AVALIAÇÃO",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .width(350.dp)
                .height(550.dp)
                .background(Color(0x0FFFFFFF), shape = RoundedCornerShape(15.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.barbearialogin),
                    contentDescription = "Logo TM",
                    Modifier
                        .size(240.dp)
                        .align(Alignment.Center)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(35.dp, 40.dp, 35.dp, 80.dp)
            ) {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= estrelasSelecionadas) Icons.Filled.Star else Icons.Outlined.Star,
                        contentDescription = "Estrela $i",
                        tint = if (i <= estrelasSelecionadas) Color.Yellow else Color.White,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                estrelasSelecionadas = i // Atualiza a quantidade de estrelas selecionadas
                            }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },  // ação de cancelamento
                    modifier = Modifier
                        .width(160.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.btn_vermelho),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.cancelar_button),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }

                Button(
                    onClick = {
                        viewModel.postAvaliacao(estrelasSelecionadas,
                            onSuccess = {
                                Toast.makeText(context, "Avaliação cadastrada com sucesso!", Toast.LENGTH_SHORT).show()
                            },
                            onError = { errorMessage ->
                                Toast.makeText(context, "Erro ao cadastrar avaliação: $errorMessage", Toast.LENGTH_SHORT).show()
                            })
                    },
                    modifier = Modifier
                        .width(160.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.btn_cadastrar),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.salvar_button),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                }
            }
        }
        IconRowClient(navController, R.drawable.pngestrela)
    }
}

@Preview(showBackground = true)
@Composable
fun AvaliacaoPreview() {
    mobile_appTheme {
        val navController = rememberNavController()
        Avaliacao(navController = navController)
    }
}
