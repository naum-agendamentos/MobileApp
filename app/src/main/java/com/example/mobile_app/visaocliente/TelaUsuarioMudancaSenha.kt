import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import com.example.homepage.R

@Composable
fun PasswordChangeScreen() {
    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val backgroundImage =
        painterResource(id = R.drawable.telafundo)

    val logobarbeiro =
        painterResource(id = R.drawable.logobarbeiro)

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
                .fillMaxSize(),
        ) {
            Text(
                text = "MEU PERFIL",
                style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            ),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 80.dp)
            )
        }

        // Centraliza a Column
        Column(
            modifier = Modifier
                .height(600.dp)
                .padding(50.dp)
                .background(Color(0xBFFFFFFF), shape = RoundedCornerShape(12.dp))
                .align(Alignment.Center), // Mover o modificador align para cá
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(60.dp))

            OutlinedTextField(
                value = currentPassword,
                onValueChange = { currentPassword = it },
                label = { Text(text = "Senha Atual") },
                modifier = Modifier
                    .width(280.dp)
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(26.dp))


            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text(text = "Nova Senha") },
                modifier = Modifier
                    .width(280.dp)
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )


            Spacer(modifier = Modifier.height(26.dp))


            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirme a Senha") },
                modifier = Modifier
                    .width(280.dp)
                    .height(56.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )



            Spacer(modifier = Modifier.height(120.dp))



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF333333)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "CANCELAR", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00A12D)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "SALVAR", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }



        }

        Footer(modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
        )

    }
}



@Composable
fun TextFieldWithLabel(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Home",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }
    }
}




@Preview
@Composable
fun PreviewPasswordChangeScreen() {
    PasswordChangeScreen()
}
