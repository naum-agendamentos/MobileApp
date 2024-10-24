package com.example.mobile_app.visaobarbeiro.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize // Importação correta
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier // Importação correta
import androidx.compose.ui.graphics.Color


@Composable
fun BlackBackground(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF333333))) {
        content()
    }
}
