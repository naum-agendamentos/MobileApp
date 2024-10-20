import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
fun MuralEdicao(titulo: String, descricao: String, navController: NavController, viewModel: MuralViewModel = viewModel()) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    var currentTitulo by remember { mutableStateOf(titulo) }
    var currentDescricao by remember { mutableStateOf(descricao) }

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

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .width(370.dp)
                    .height(600.dp)
                    .background(
                        colorResource(id = R.color.preto),
                        RoundedCornerShape(12.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            ) {
                Column() {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.seta_retorno),
                            contentDescription = "Descrição da imagem",
                            modifier = Modifier
                                .size(50.dp)
                                .padding(start = 8.dp)
                        )

                        Text(
                            text = "EDITAR AVISO",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.White
                            ),
                            modifier = Modifier.padding(start = 35.dp)
                        )
                    }

                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Titulo:",
                            style = TextStyle(fontSize = 25.sp, color = Color.White)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = currentTitulo,
                            onValueChange = { currentTitulo = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(0.dp, Color.Transparent, RoundedCornerShape(0.dp))
                                .padding(bottom = 4.dp)
                        )

                        Text(
                            text = "Descrição:",
                            style = TextStyle(fontSize = 25.sp, color = Color.White)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        OutlinedTextField(
                            value = currentDescricao,
                            onValueChange = { currentDescricao = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(0.dp, Color.Transparent, RoundedCornerShape(0.dp))
                                .padding(bottom = 4.dp)
                        )

                        // Botões para salvar ou cancelar
                        Spacer(modifier = Modifier.height(30.dp))

                        Row {
                            Button(
                                onClick = {
                                    // Atualiza a itemAtual na ViewModel com os dados do usuário
                                    viewModel.itemAtual = viewModel.itemAtual.copy(titulo = currentTitulo, descricao = currentDescricao)
                                    viewModel.salvar()
                                    // Voltar para a tela anterior após salvar
                                    navController.popBackStack()
                                },
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.btn_editar),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(
                                    text = "Editar",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }

                            Button(
                                onClick = {
                                    // Voltar sem salvar
                                    navController.popBackStack()
                                },
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.btn_cancelar),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(
                                    text = "Cancelar",
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
  //  IconRow()
}

@Preview
@Composable
fun AvisoEditar() {
    // Passar valores default para a pré-visualização
    val navController = rememberNavController() // Utilize um NavController fictício
    MuralEdicao(titulo = "Título de Exemplo", descricao = "Descrição de Exemplo", navController = navController)
}
