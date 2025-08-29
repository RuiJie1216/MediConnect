package com.example.mediconnect.ui.doctorTheme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorHomeScreen(
    modifier: Modifier = Modifier,
    onAppointmentClick: () -> Unit,
    onProfileClick: () -> Unit,
    onPatientClick: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row {
            Button(
                onClick = onAppointmentClick,
                modifier = Modifier
                    .padding(8.dp)
                    .height(125.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFCBFFFC),
                    contentColor = Color.Black
                )

            ) {
                Text("Appointment List")
            }

            Column {
                Button(
                    onClick = onProfileClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFC7FFEC),
                        contentColor = Color.Black
                    )

                ) {
                    Text("My Profile")
                }

                Button(
                    onClick = onPatientClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5EFFC9),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Patients Information")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Current Appointment",
            style = MaterialTheme.typography.titleMedium,
            fontStyle = FontStyle.Italic,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(8.dp))

        Column(modifier = modifier
            .align(Alignment.CenterHorizontally)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95F)

                    .height(150.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(12.dp)
                    )

                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📅 Date: 17/08/2025",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Lew Yao Bing - 8:00AM",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Tan Chin Hua - 8:30AM",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Lee Kuan Ying - 9:00AM",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

            }
        }
    }
}
@Composable
fun DocMain() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        //composable("home") { DoctorHomeScreen(navController, modifier = Modifier) }
        //composable("patients") { PatientsScreen(navController) }
        composable("appointments") { AppointmentScreen() }
        composable("profile") { ProfileScreen() }
        composable("consultation") { ConsultationScreen() }
        composable("patientDetail") { PatientDetailScreen() }
    }
}




//RJ: 每个screen一个 kt file，这样preview容易看

@Composable
fun AppointmentScreen(){

}
@Composable
fun ProfileScreen() {
    Text("Profile Screen")
}

@Composable
fun ConsultationScreen() {
    Text("Consultation Records Screen")
}

@Composable
fun PatientDetailScreen() {
    Text("Patient Detail Screen")
}

@Preview(showBackground = true)
@Composable
fun FinalBright() {
    DoctorHomeScreen(
        onPatientClick = {},
        onProfileClick = {},
        onAppointmentClick = {}
    )
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FinalDark() {
    DoctorHomeScreen(
        onPatientClick = {},
        onProfileClick = {},
        onAppointmentClick = {}
    )
}
/*
fun Logout(navController: NavController) {

    navigationIcon = { IconButton(onClick = { navController.navigate("login") }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }
    }
}
*/