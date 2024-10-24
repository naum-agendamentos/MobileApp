package com.example.mobile_app

import NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobile_app.visaobarbeiro.componentes.BlackBackground


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            BlackBackground {
                NavGraph()
            }

        }
    }
}
//
//@Composable
//fun MyApp() {
//    val muralViewModel: MuralViewModel = viewModel()
//    AppNavigation(muralViewModel) // Chama a navegação
//}



