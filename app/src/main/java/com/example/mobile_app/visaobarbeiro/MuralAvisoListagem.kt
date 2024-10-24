import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.homepage.visaocliente.componentes.muralcomponentes.MuralViewModel
import com.example.mobile_app.R
<<<<<<< HEAD
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
=======
import com.example.mobile_app.visaobarbeiro.IconRow
import com.example.mobile_app.visaobarbeiro.navBarb
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
>>>>>>> main


@Composable
fun MuralListagem(navController: NavHostController, viewModel: MuralViewModel = viewModel()) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                navBarb()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.mural_de_avisos), // Usando string do resources
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.icon_adicionar),
                    contentDescription = stringResource(id = R.string.icone_adicionar_desc),
                    modifier = Modifier
                        .size(70.dp)
                        .padding(16.dp)
                        .clickable {
                            // Navegar para MuralCriacao
                            navController.navigate("muralCriacao")
                        }
                )
            }


            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(570.dp)
                    .background(
                        colorResource(id = R.color.preto),
                        RoundedCornerShape(12.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                LazyColumn(
                    modifier = Modifier.padding(10.dp)
                ) {
                    items(items = viewModel.getAvisos()) { item ->
                        // Formatação da data com '/'
                        val dataFormatada = item.data?.let {
                            val partes = it.split("T")
                            "${partes[0].split("-").reversed().joinToString("/")} - ${partes[1]}"
                        } ?: "Data não disponível" // Caso a data seja nula

                        Text(
                            text = dataFormatada,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(8.dp)
                        )

                        // Definindo a cor da borda com base no tipoAviso
                        val borderColor = when (item.tipoAviso) {
                            "INFORMACAO" -> Color.Blue
                            "ALERTA" -> Color.Yellow
                            "URGENTE" -> Color.Red
                            else -> Color.Transparent // Cor padrão se não corresponder
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    borderColor,
                                    RoundedCornerShape(5.dp)
                                )
                                .padding(8.dp)
                        ) {
                            Column {
                                Text(
                                    text = "${item.titulo}",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp,
                                        color = Color.White
                                    ),
                                    modifier = Modifier.padding(8.dp)
                                )

                                Text(
                                    text = "${item.descricao}",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    ),
                                    modifier = Modifier.padding(8.dp)
                                )

                                Button(
                                    onClick = {
                                        navController.navigate("muralEdicao/${item.id}/${item.titulo}/${item.descricao}")
                                    },
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(40.dp)
                                        .padding(horizontal = 8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(id = R.color.btn_editar),
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.editar_button), // Correção
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
       // IconRow()
    }
}

@Preview
@Composable
fun MuralAvisoListagemPreview() {
    val navController = rememberNavController() // Cria um navController fictício
    MuralListagem(navController = navController)
}
