package com.example.mediconnect.ui.doctorTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
fun DoctorHomeScreen(navController: NavHostController, modifier: Modifier ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = "MEDICONNECT",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00C8B3),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row {
                Button(
                    onClick = { navController.navigate("appointments") },
                    modifier = Modifier
                        .padding(8.dp)
                        .height(125.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFCBFFFC),
                        contentColor = Color.Black
                    )

                ) {
                    Text("Appointment List")
                }

                Column {
                    Button(
                        onClick = { navController.navigate("profile") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC7FFEC),
                            contentColor = Color.Black
                        )

                    ) {
                        Text("My Profile")
                    }

                    Button(
                        onClick = { navController.navigate("patients") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
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
                        Divider(
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
}
@Composable
fun MediConnectApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { DoctorHomeScreen(navController, modifier = Modifier) }
        composable("patients") { PatientsScreen() }
        composable("appointments") { AppointmentScreen() }
        composable("profile") { ProfileScreen() }
        composable("consultation") { ConsultationScreen() }
        composable("patientDetail") { PatientDetailScreen() }
    }
}

@Composable
fun PatientsScreen() {

}
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
fun Final() {
    DoctorHomeScreen(navController = rememberNavController(), modifier = Modifier)
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