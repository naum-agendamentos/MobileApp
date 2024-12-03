package com.example.mobile_app.visaobarbeiro.telas_barbeiro.bloqueio_de_dia

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mobile_app.R
import com.example.mobile_app.ui.theme.mobile_appTheme
import com.example.mobile_app.visaobarbeiro.componentes.IconRow
import com.example.mobile_app.visaobarbeiro.componentes.navBarb
import com.example.mobile_app.visaobarbeiro.telas_barbeiro.BarbeirosViewModel
import com.example.mobile_app.visaobarbeiro.ver_barbeiro.componente.CardBarbeiro

@Composable
fun BarbeiroBloqueioDiaHora(viewModel: BarbeirosViewModel = viewModel(), navController: NavController) {
    val backgroundImage = painterResource(id = R.drawable.fundo_barbeiro)
    val barbeiros = remember { viewModel.barbeiros }
    val isLoading by viewModel.isLoading // Atualização aqui

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        navBarb(navController = navController)

        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 90.dp, start = 74.dp, end = 74.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Text(
                    text = "BLOQUEAR DIA",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // Use LaunchedEffect para recarregar barbeiros ao entrar na tela
        LaunchedEffect(Unit) {
            viewModel.fetchBarbeiros()
        }

        if (isLoading) { // Alteração aqui
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(350.dp)
                    .height(550.dp)
                    .background(Color(0x0FFFFFFF), shape = RoundedCornerShape(15.dp))
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(barbeiros) { barbeiro ->
                        barbeiro.foto?.let { imageUrl ->
                            Log.d("TelaBarbeiros", "ID do barbeiro: ${barbeiro.id}") // Adicione este log
                            CardBarbeiro(name = barbeiro.nome, imageUrl = imageUrl) {
                                // Criando uma string JSON com os dados do barbeiro
                                val semanaJson = """
                                                    {
                                                        "domingo": "${barbeiro.semana?.domingo}",
                                                        "segunda": "${barbeiro.semana?.segunda}",
                                                        "terca": "${barbeiro.semana?.terca}",
                                                        "quarta": "${barbeiro.semana?.quarta}",
                                                        "quinta": "${barbeiro.semana?.quinta}",
                                                        "sexta": "${barbeiro.semana?.sexta}",
                                                        "sabado": "${barbeiro.semana?.sabado}"
                                                    }
                                                """.trimIndent()
                                Log.e("SEMANA", semanaJson)
                                navController.navigate("/bloqueio/${barbeiro.id}/${Uri.encode(semanaJson)}") // Navegando com ID e JSON codificado
                            }
                        }
                    }
                }
            }
        }
    }

    IconRow(navController = navController, activeIcon = R.drawable.pngrelogio)
}

@Preview(showBackground = true)
@Composable
fun BarbeiroBloqueioDiaHoraPreview() {
    mobile_appTheme {
        val navController = rememberNavController()
        BarbeiroBloqueioDiaHora(navController = navController)
    }
}