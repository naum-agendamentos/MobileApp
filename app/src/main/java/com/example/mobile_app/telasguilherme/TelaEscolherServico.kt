import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.mobile_app.R
import com.example.mobile_app.visaobarbeiro.IconRow

@Composable
fun ServicoEscolha() {
    val backgroundImage = painterResource(id = R.drawable.fundo_cliente)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.logobarbeiro),
            contentDescription = "Login image",
            modifier = Modifier
                .size(60.dp)
                .padding(start = 5.dp)
                .padding(top = 10.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "AGENDAMENTOS",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 90.dp)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(500.dp)
                    .offset(y = -20.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    // Adicionando os itens de serviço
                    ServicoItem2(
                        nome = "Corte simples",
                        preco = "R$ 40,00",
                        duracao = "30 Min",
                        isSelected = true
                    )

                    Spacer(modifier = Modifier.padding(7.dp))

                    ServicoItem2(
                        nome = "Barba simples",
                        preco = "R$ 45,00",
                        duracao = "30 Min",
                        isSelected = true
                    )

                    Spacer(modifier = Modifier.padding(7.dp))

                    ServicoItem2(
                        nome = "Corte feminino simples",
                        preco = "R$ 50,00",
                        duracao = "60 Min",
                        isSelected = false
                    )

                    Spacer(modifier = Modifier.padding(7.dp))

                    ServicoItem2(
                        nome = "Platinado",
                        preco = "R$ 60,00",
                        duracao = "120 Min",
                        isSelected = false
                    )
                    Box(
                        modifier = Modifier.fillMaxSize() // Preenche toda a tela
                    ) {
                        Button(
                            onClick = { /* Ação ao clicar no botão */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue), // Define a cor de fundo do botão
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 32.dp) // Ajusta o espaçamento para baixo e para a esquerda
                                .align(Alignment.BottomEnd) // Posiciona o botão no canto inferior esquerdo
                        ) {
                            Text(text = "Próximo", color = Color.White) // Texto dentro do botão
                        }
                    }



                }


            }

            Text(
                text = "10H00",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )
        }
    }
    IconRow()
}

@Composable
fun ServicoItem2(
    nome: String,
    preco: String,
    duracao: String,
    isSelected: Boolean
) {
    var checked by remember { mutableStateOf(isSelected) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = nome, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = preco, fontSize = 14.sp)
            Text(text = duracao, fontSize = 14.sp)
        }

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (checked) Color.Blue else Color.White,
                    shape = RoundedCornerShape(4.dp)
                )
                .border(2.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                .clickable { checked = !checked },
            contentAlignment = Alignment.Center
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Checked",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun EscolherServico() {
    ServicoEscolha()
}
