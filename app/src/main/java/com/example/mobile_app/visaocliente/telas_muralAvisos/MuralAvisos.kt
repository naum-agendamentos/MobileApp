import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.visaocliente.componentes.FundoTela
import com.example.mobile_app.visaocliente.componentes.IconRowClient

@Composable
fun MuralAvisos(navController: NavController) {
    FundoTela {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color.White.copy(alpha = 0.5f),
            shadowElevation = 3.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.75f)
                    .background(color = Color.White.copy(0.5f))
                    .border(1.dp, Color.Black, RoundedCornerShape(12.dp))
            ) {
                Mural()
            }
        }
    }
    IconRowClient(navController = navController, activeIcon = R.drawable.pngalertauser)
}

@Preview(showSystemUi = false)
@Composable
fun MuralPreview() {
    val fakeNavController = rememberNavController()
    MuralAvisos(navController = fakeNavController)
}