package com.example.mobile_app

import Footer
import PasswordChangeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobile_app.visaocliente.Cadastro
import com.example.mobile_app.visaocliente.Login

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Login()
            Cadastro()
            Footer()
            PasswordChangeScreen()
        }
    }
}



