import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobile_app.R

@Composable
fun Mural(viewModel: MuralViewModel = viewModel()) {
    val avisos = viewModel.avisos

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {
            Text(
                text = stringResource(R.string.title_activity_mural),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            if (avisos.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(5.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(2.dp, Color.Gray)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Nenhum aviso publicado",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
                }
            } else {
                // Inverter a lista de avisos para exibir o último item primeiro
                val avisosInvertidos = avisos.reversed()

                items(items = avisosInvertidos) { item ->
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
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(8.dp)
                    )

                    // Definindo a cor da borda com base no tipoAviso
                    val borderColor = when (item.tipoAviso) {
                        "INFORMACAO" -> Color.Blue
                        "ALERTA" -> Color(0xFFFFA500)
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
                                text = item.titulo ?: "Título não disponível",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp,
                                    color = Color.Black
                                ),
                                modifier = Modifier.padding(8.dp)
                            )

                            Text(
                                text = item.descricao ?: "Descrição não disponível",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color.Black
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

@Preview
@Composable
fun PreviewMural() {
    Mural()
}
