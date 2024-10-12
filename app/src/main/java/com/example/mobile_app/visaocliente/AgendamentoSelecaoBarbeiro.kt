import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
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
import com.example.mobile_app.R

data class Person(val name: String, val imageResId: Int)

@Composable
fun AgendamentoBarbeiro() {
    val backgroundImage = painterResource(id = R.drawable.telafundo)
    val people = listOf(
        Person("Bryan Liares", R.drawable.barbeiro1),
        Person("Person 2", R.drawable.barbeiro2),
        Person("Person 3", R.drawable.barbeiro3)
    )
    var currentIndex by remember { mutableStateOf(0) }

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
                    .height(550.dp)
                    .offset(y = -20.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Carrossel(
                        people = people,
                        currentIndex = currentIndex,
                        onNext = { currentIndex = (currentIndex + 1) % people.size },
                        onPrevious = { currentIndex = (currentIndex - 1 + people.size) % people.size }
                    )
                }
            }
        }
    }
}

@Composable
fun Carrossel(
    people: List<Person>,
    currentIndex: Int,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {
    val person = people[currentIndex]

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous",
                modifier = Modifier
                    .size(40.dp)
                    .offset(y = 210.dp)
                    .clickable { onPrevious() }

            )

            Image(
                painter = painterResource(id = person.imageResId),
                contentDescription = person.name,
                modifier = Modifier
                    .height(350.dp)
                    .width(200.dp)
                    .offset(y = 10.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next",
                modifier = Modifier
                    .size(40.dp)
                    .offset(y = 210.dp)
                    .clickable { onNext() }
            )
        }

Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = person.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 35.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { }) {
                    Text(text = "ANTERIOR")
                }

                Button(onClick = {  }) {
                    Text(text = "PRÓXIMO")
                }
            }
        }



    }
}

@Preview
@Composable
fun AgendamentoBarbeiros() {
    AgendamentoBarbeiro()
}
