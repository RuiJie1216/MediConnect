package com.example.mediconnect.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediconnect.AppScreen
import com.example.mediconnect.R

@Composable
fun LoginChooseButtonBar(
    modifier: Modifier = Modifier,
    chooseBar: AppScreen,
    onTurnPageClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 60.dp)
            .size(60.dp)
            .background(
                Color(0xFFEBCCFF),
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                ) {
                    Button(
                        onClick = {if (chooseBar != AppScreen.UserLogin) onTurnPageClick() },
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .height(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (chooseBar == AppScreen.UserLogin) Color(0xFFDEB5FA) else Color(0xFFEBCCFF)
                        )
                    ) {}

                    Icon(
                        imageVector = Icons.Outlined.PeopleAlt,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(start = 67.dp, top = 5.dp)
                    )
                }

                Box(
                    modifier = Modifier
                ) {
                    Button(
                        onClick = {if (chooseBar != AppScreen.DoctorLogin) onTurnPageClick() },
                        modifier = Modifier
                            .padding(end = 50.dp)
                            .height(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (chooseBar == AppScreen.DoctorLogin) Color(0xFFDEB5FA) else Color(0xFFEBCCFF)
                        )
                    ) {}

                    Image(
                        painter = painterResource(R.drawable.doctor),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 17.dp, top = 3.dp)
                            .size(25.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Users",
                    fontSize = 13.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(start = 60.dp)
                )

                Text(
                    text = "Doctors",
                    fontSize = 13.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(end = 55.dp)
                )
            }

        }


    }
}