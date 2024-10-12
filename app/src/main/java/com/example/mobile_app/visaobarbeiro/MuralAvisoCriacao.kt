import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.IconRow
import com.example.mobile_app.visaobarbeiro.navBarb

enum class ButtonType {
    INFO, ALERT, URGENT
}

@Composable
fun MuralCriacao(modifier: Modifier = Modifier) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    var selectedButton by remember { mutableStateOf<ButtonType?>(null) }

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
                        modifier = Modifier
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.seta_retorno), // Substitua pelo seu recurso de imagem
                            contentDescription = "Descrição da imagem",
                            modifier = Modifier
                                .size(50.dp) // Ajuste o tamanho da imagem conforme necessário
                                .padding(start = 8.dp) // Espaçamento entre a imagem e o texto
                        )

                        Text(
                            text = "CRIAÇÃO DE AVISO",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp,
                                color = Color.White
                            ),
                            modifier = Modifier
                                .padding(start = 15.dp)
                        )
                    }

                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Titulo:",
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White
                            )
                        )

                        // Adicionando um espaçamento entre o Text e o OutlinedTextField
                        Spacer(modifier = Modifier.height(15.dp)) // Define o espaçamento desejado

                        OutlinedTextField(
                            value = titulo,
                            onValueChange = { titulo = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    0.dp,
                                    Color.Transparent,
                                    RoundedCornerShape(0.dp)
                                ) // Remove as bordas padrão do OutlinedTextField
                                .padding(bottom = 4.dp) // Espaçamento para a borda inferior
                        )


                        Text(
                            text = "Descrição:",
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White
                            )
                        )

                        // Adicionando um espaçamento entre o Text e o OutlinedTextField
                        Spacer(modifier = Modifier.height(15.dp)) // Define o espaçamento desejado

                        OutlinedTextField(
                            value = descricao,
                            onValueChange = { descricao = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .border(
                                    0.dp,
                                    Color.Transparent,
                                    RoundedCornerShape(0.dp)
                                ) // Remove as bordas padrão do OutlinedTextField
                                .padding(bottom = 4.dp) // Espaçamento para a borda inferior
                        )

                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "Urgência:",
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = Color.White,
                            )
                        )

                        Row {
                            Button(
                                onClick = {
                                    selectedButton =
                                        if (selectedButton == ButtonType.INFO) null else ButtonType.INFO
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType.INFO) colorResource(
                                        id = R.color.btn_editar
                                    ) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(100.dp) // Faz os botões ocuparem o mesmo espaço
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(
                                        width = 0.5.dp, // Largura da borda
                                        color = Color.White, // Cor da borda
                                        shape = RoundedCornerShape(12.dp) // Forma da borda
                                    )
                            ) {
                                Text(text = "Info")
                            }
                            Button(
                                onClick = {
                                    selectedButton =
                                        if (selectedButton == ButtonType.ALERT) null else ButtonType.ALERT
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType.ALERT) colorResource(
                                        id = R.color.btn_editar
                                    ) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(110.dp) // Faz os botões ocuparem o mesmo espaço
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(
                                        width = 0.5.dp, // Largura da borda
                                        color = Color.White, // Cor da borda
                                        shape = RoundedCornerShape(12.dp) // Forma da borda
                                    )
                            ) {
                                Text(text = "Alerta")
                            }
                            Button(
                                onClick = {
                                    selectedButton =
                                        if (selectedButton == ButtonType.URGENT) null else ButtonType.URGENT
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedButton == ButtonType.URGENT) colorResource(
                                        id = R.color.btn_editar
                                    ) else Color.Transparent,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .width(120.dp) // Faz os botões ocuparem o mesmo espaço
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp)
                                    .border(
                                        width = 0.5.dp, // Largura da borda
                                        color = Color.White, // Cor da borda
                                        shape = RoundedCornerShape(12.dp) // Forma da borda
                                    )
                            ) {
                                Text(text = "Urgente")
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row {
                            Button(
                                {},
                                Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.btn_cancelar), // Cor de fundo do botão "Info"
                                    contentColor = Color.White   // Cor do texto do botão "Info"
                                ),
                                shape = RoundedCornerShape(10.dp),

                                ) {
                                Text(
                                    text = "Cancelar",
                                    fontSize = 16.sp, // Define o tamanho da fonte
                                    fontWeight = FontWeight.Bold, // Define o peso da fonte (negrito, normal, etc.)
                                )
                            }

                            Button(
                                {},
                                Modifier
                                    .width(140.dp)
                                    .height(50.dp)
                                    .padding(horizontal = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.btn_cadastrar),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Text(
                                    text = "Cadastrar",
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
    IconRow()
}

@Preview
@Composable
fun MuralAviso() {
    MuralCriacao()
}
