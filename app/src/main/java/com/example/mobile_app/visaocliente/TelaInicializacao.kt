import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobile_app.R
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun TelaInicializacao() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logobarbeiro),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(250.dp)
            )

            Text(
                text = "Barbearia",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Light, fontSize = 22.sp)) {
                        append("Uma parceria: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 22.sp)) {
                        append("NA.UM")
                    }
                },
                style = TextStyle(fontSize = 16.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InicioTela() {
    TelaInicializacao()
}
