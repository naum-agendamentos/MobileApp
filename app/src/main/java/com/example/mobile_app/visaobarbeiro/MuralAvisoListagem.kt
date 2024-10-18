import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homepage.visaocliente.componentes.muralcomponentes.MuralViewModel
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.IconRow
import com.example.mobile_app.visaobarbeiro.navBarb
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun MuralListagem(navController: NavController, viewModel: MuralViewModel = viewModel(), modifier: Modifier = Modifier) {
    val avisos = viewModel.getAvisos()
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
                    contentDescription = stringResource(id = R.string.icone_adicionar_desc), // Usando string do resources
                    modifier = Modifier
                        .size(50.dp)
                        .padding(start = 8.dp)
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
                LazyColumn {
                    items(items = viewModel.getAvisos()) { item ->
                        Box() {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(
                                    text = stringResource(id = R.string.data_exemplo), // Usando string do resources
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    ),
                                    modifier = Modifier.padding(8.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(
                                            1.dp,
                                            Color.White,
                                            RoundedCornerShape(5.dp)
                                        )
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

                                        ClickableText(
                                            text = AnnotatedString(stringResource(id = R.string.editar_aviso)), // Usando string do resources
                                            style = TextStyle(fontSize = 15.sp),
                                            modifier = Modifier.padding(5.dp)
                                        ) {
                                            viewModel.itemAtual = item
                                            navController.navigate("muralEdicao/${item.titulo}/${item.descricao}")
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    }
                }
            }
        }

    }

    IconRow()
}


@Preview
@Composable
fun MuralBarbeiro(navController: NavController = rememberNavController()) {
    MuralListagem(navController = navController)  // Passando o navController
}
