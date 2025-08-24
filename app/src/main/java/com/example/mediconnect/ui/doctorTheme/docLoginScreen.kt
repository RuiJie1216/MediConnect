package com.example.mediconnect.ui.doctorTheme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconnect.R

@Composable
fun DoctorLoginScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.loginpage),
            contentDescription = null
        )
        Box(
            modifier = modifier
                .fillMaxWidth(),
        ) {
            Card(
                modifier = modifier
            ) {
                Column(
                    modifier = Modifier
                ) {
                    Text(
                        text = "ji"
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DoctorLoginScreenPreview() {
    DoctorLoginScreen()
}