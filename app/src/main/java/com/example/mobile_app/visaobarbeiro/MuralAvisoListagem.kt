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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.homepage.R
import com.example.homepage.visaobarbeiro.IconRow
import com.example.homepage.visaobarbeiro.navBarb

@Composable
fun MuralListagem() {
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
                verticalAlignment = Alignment.CenterVertically, // Alinha verticalmente os itens
                horizontalArrangement = Arrangement.SpaceBetween // Espaça os itens
            ) {
                // Coloca um Box para centralizar o texto
                Box(
                    modifier = Modifier.weight(1f), // Faz o Box ocupar o espaço restante
                    contentAlignment = Alignment.Center // Centraliza o texto dentro do Box
                ) {
                    Text(
                        text = "MURAL DE AVISOS",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    )
                }

                // Imagem ou ícone à direita

                Image(
                    painter = painterResource(id = R.drawable.icon_adicionar), // Substitua pelo seu recurso de imagem
                    contentDescription = "Descrição da imagem",
                    modifier = Modifier
                        .size(50.dp) // Ajuste o tamanho da imagem conforme necessário
                        .padding(start = 8.dp) // Espaçamento entre a imagem e o texto
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
            ){
                Column {
                    Box(){
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "10/09/2024 - 18:42",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.White
                                ),
                                modifier = Modifier.padding(8.dp)
                            )

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    Color.White,
                                    RoundedCornerShape(5.dp)
                                )
                            ){
                                Column {
                                    Text(
                                        text = "Desconto em todos os produtos capilares durante o dia de hoje !!!",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 24.sp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.padding(8.dp)
                                    )

                                    Text(
                                        text = "Alguns dos produtos em promoção: Pomada, Gel, Pomada em pó ...",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(15.dp)) // Define o espaçamento desejado

                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    Color.White,
                                    RoundedCornerShape(5.dp)
                                )
                            ){
                                Column {
                                    Text(
                                        text = "Desconto em todos os produtos capilares durante o dia de hoje !!!",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 24.sp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.padding(8.dp)
                                    )

                                    Text(
                                        text = "Alguns dos produtos em promoção: Pomada, Gel, Pomada em pó ...",
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp,
                                            color = Color.White
                                        ),
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }
                        }

                    }

                }
            }

        }

    }

    IconRow()
}


// MUDAR PARA OS ATRIBUTOS DO MURAL: TITULO, DESCRIÇÃO, URGÊNCIA... (SE NECESSÁRIO)

//@Composable
//fun MuralDeAviso(
//    nome: String,
//    preco: String,
//    duracao: String,
//    isSelected: Boolean
//) {
//    var checked by remember { mutableStateOf(isSelected) }
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFCCCCFF), RoundedCornerShape(12.dp))
//            .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
//            .padding(8.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(
//            modifier = Modifier.padding(start = 8.dp)
//        ) {
//            Text(text = nome, fontWeight = FontWeight.Bold, fontSize = 16.sp)
//            Text(text = preco, fontSize = 14.sp)
//            Text(text = duracao, fontSize = 14.sp)
//        }
//
//        Box(
//            modifier = Modifier
//                .size(40.dp)
//                .background(
//                    if (checked) Color.Blue else Color.White,
//                    shape = RoundedCornerShape(4.dp)
//                )
//                .border(2.dp, Color.Black, shape = RoundedCornerShape(4.dp))
//                .clickable { checked = !checked },
//            contentAlignment = Alignment.Center
//        ) {
//            if (checked) {
//                Icon(
//                    imageVector = Icons.Default.Check,
//                    contentDescription = stringResource(id = R.string.checked),
//                    tint = Color.White
//                )
//            }
//        }
//    }
//}

@Preview
@Composable
fun MuralBarbeiro() {
    MuralListagem()
}
