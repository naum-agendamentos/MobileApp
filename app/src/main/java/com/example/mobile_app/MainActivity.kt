package com.example.mobile_app

import MuralListagem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.Mural

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MuralListagem()
//            MyApp()
        }
    }
}
//
//@Composable
//fun MyApp() {
//    val muralViewModel: MuralViewModel = viewModel()
//    AppNavigation(muralViewModel) // Chama a navegação
//}



