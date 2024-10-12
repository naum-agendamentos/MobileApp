package com.example.mobile_app.visaocliente.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Navigation() {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Gray),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .shadow(elevation = 100.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center

            ){
                Icon(
                    imageVector = Icons.Filled.Campaign,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }

        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }

        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center

            ){
                Icon(
                    imageVector = Icons.Filled.CalendarToday,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }


        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center

            ){
                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }


        Surface(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.offset(y = (-20).dp)
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center

            ){
                Icon(
                    imageVector = Icons.Filled.PersonOutline,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)

                )
            }
        }
    }
}


@Preview(heightDp = 100, showBackground = true)
@Composable
fun NavigatioPreview(){
    Column(
        modifier = Modifier.fillMaxSize().height(300.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Navigation()
    }
}
