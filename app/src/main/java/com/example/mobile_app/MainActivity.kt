package com.example.mobile_app

import AppNavigation
import Footer
import PasswordChangeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homepage.visaocliente.componentes.muralcomponentes.MuralViewModel
import com.example.mobile_app.visaocliente.Cadastro
import com.example.mobile_app.visaocliente.Login
import com.example.mobile_app.visaocliente.componentes.muralcomponentes.Mural
import com.example.mobile_app.visaocliente.pages.MuralAvisos

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mural()
//            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val muralViewModel: MuralViewModel = viewModel()
    AppNavigation(muralViewModel) // Chama a navegação
}



