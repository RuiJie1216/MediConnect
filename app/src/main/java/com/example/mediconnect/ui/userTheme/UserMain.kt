package com.example.mediconnect.ui.userTheme

import com.example.mediconnect.R


import android.os.Bundle
import androidx.lifecycle.viewmodel.compose.viewModel
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key.Companion.F
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object MainMenu : Screen("main_menu")
    object Appointment : Screen("appointment")
    object MedicalReminder : Screen("medical_reminder")
    object Profile : Screen("user_profile")
    object PersonaInfo : Screen("persona_info")
    object History : Screen("history")
    object Bill : Screen("bill")
    object Setting : Screen("setting")
    object Logout : Screen("logout")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainMenu.route
                ) {
                    composable(Screen.MainMenu.route) {
                        User_Menu(navController)
                    }
                    composable(Screen.Appointment.route) {
                        AppointmentScreen(navController)
                    }
                    composable(Screen.MedicalReminder.route) {
                        MedicalReminderScreen(navController)
                    }
                    composable(Screen.Profile.route){
                        Profile(navController)
                    }
                    composable(Screen.PersonaInfo.route){
                        PersonalInfo(navController)
                    }
                    composable(Screen.History.route){
                        History(navController)
                    }
                    composable(Screen.Bill.route){
                        Bill(navController)
                    }
                    composable(Screen.Setting.route){
                        Setting(navController)
                    }
                    composable(Screen.Logout.route){
                        Logout(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun User_Menu(navController:NavController) {
    Column(
        modifier =Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "MEDICONNECT",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C8B3))
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(16.dp)
                .height(50.dp)
                .background(Color.White, RoundedCornerShape(25.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(25.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "Search...",
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = {
                    navController.navigate(Screen.Appointment.route)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xBFA6E8A9)
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)

            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Appointment",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Appointment",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate(Screen.MedicalReminder.route)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .height(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xBFA6E8A9)
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Medical Reminder",
                        modifier = Modifier.size(50.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Medical Reminder",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(25.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .border(2.dp, Color.Black, RoundedCornerShape(12.dp))
        ){
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ){
                Text(
                    text = "Your Appointment",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Divider(color = Color.Black, thickness = 2.dp)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Appointment details will be shown here",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(35.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.hospital1),
                contentDescription = "Hospital 1",
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(id = R.drawable.hospital2),
                contentDescription = "Hospital 2",
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(25.dp))
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .height(70.dp)
                .background(Color(0x2600C8B3), RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.Appointment.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Appointment",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Appointment",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {navController.navigate(Screen.MainMenu.route)}
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(28.dp),
                        tint = Color(0xFF00C8B3)
                    )
                    Text(
                        text = "Home",
                        fontSize = 12.sp,
                        color = Color(0xFF00C8B3),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {navController.navigate(Screen.Profile.route)}
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Profile",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AppointmentScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .height(70.dp)
                .background(Color(0x2600C8B3), RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.Appointment.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Appointment",
                        modifier = Modifier.size(28.dp),
                        tint = Color(0xFF00C8B3)
                    )
                    Text(
                        text = "Appointment",
                        fontSize = 12.sp,
                        color = Color(0xFF00C8B3),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {navController.navigate(Screen.MainMenu.route)}
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Home",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.Profile.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Profile",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MedicalReminderScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
}

@Composable
fun Profile(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val personalInfo by viewModel.personalInfo.collectAsState()

    // Load user data
    LaunchedEffect(Unit) {
        viewModel.loadUserData("current_user_id")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "MEDICONNECT",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C8B3))
                .padding(16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0x591BC2B1),
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
                .border(
                    1.dp, Color(0x591BC2B1),
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
        ) {
            Box(
                modifier = Modifier.padding(20.dp)
            ) {
                Row() {
                    Image(
                        painter = painterResource(id = R.drawable.profile_photo),
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier.padding(start = 16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = personalInfo.name.ifEmpty { "" },
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            text = personalInfo.address.ifEmpty { "" },
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "   Info",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoColumn("Age", personalInfo.age, Modifier.weight(1f))
            InfoColumn("Weight", "${personalInfo.weight} kg", Modifier.weight(1f))
            InfoColumn("Height", "${personalInfo.height} cm", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Personal info
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.PersonaInfo.route) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .height(45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Person Icon",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(20.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = "Personal info",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(20.dp),
                    tint = Color.Black
                )
            }

            // History
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.History.route) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .height(45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "History Icon",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(20.dp),
                        tint = Color.Black
                    )
                    Text("History", fontSize = 16.sp, color = Color.Black)
                }

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(20.dp),
                    tint = Color.Black
                )
            }

            // Bill
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Bill.route) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .height(45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Bill Icon",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(20.dp),
                        tint = Color.Black
                    )
                    Text(
                        "Bill",
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(20.dp),
                    tint = Color.Black
                )
            }

            // Setting
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Setting.route) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .height(45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Setting Icon",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(20.dp),
                        tint = Color.Black
                    )
                    Text(
                        "Setting", fontSize = 16.sp, color = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(20.dp),
                    tint = Color.Black
                )
            }

            // Logout
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Logout.route) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .height(45.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout Icon",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(20.dp),
                        tint = Color.Black
                    )
                    Text(
                        "Logout", fontSize = 16.sp, color = Color.Black
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(20.dp),
                    tint = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .height(70.dp)
                .background(Color(0x2600C8B3), RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .border(1.dp, Color.Black, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.Appointment.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Appointment",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Appointment",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.MainMenu.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        modifier = Modifier.size(28.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = "Home",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { navController.navigate(Screen.Profile.route) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        modifier = Modifier.size(28.dp),
                        tint = Color(0xFF00C8B3)
                    )
                    Text(
                        text = "Profile",
                        fontSize = 12.sp,
                        color = Color(0xFF00C8B3),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoColumn(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .background(Color(0x4017E12B), RoundedCornerShape(8.dp))
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PersonalInfo(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "MEDICONNECT",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF00C8B3))
                .padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                modifier = Modifier.size(28.dp),
                tint = Color.Black,
            )

            Text(
                text = "Personal Info",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Exit",
                modifier = Modifier.size(28.dp),
                tint = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_photo),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        val viewModel: UserViewModel = viewModel()
        val personalInfo by viewModel.personalInfo.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadUserData("current_user_id")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                InfoItem("Name", personalInfo.name)
                InfoItem("Age", personalInfo.age)
                InfoItem("Date of Birth", personalInfo.dob)
                InfoItem("Gender", personalInfo.gender)
                InfoItem("Email Address", personalInfo.email)
                InfoItem("Address", personalInfo.address)
                InfoItem("Phone Number", personalInfo.phone)
                InfoItem("Medical History",
                    if (personalInfo.medicalHistory.isBlank()) "No medical history"
                    else personalInfo.medicalHistory
                )
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = "$label:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = value,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun History(navController: NavController){}

@Composable
fun Bill(navController: NavController){}

@Composable
fun Setting(navController: NavController){}

@Composable
fun Logout(navController: NavController){}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        PersonalInfo(rememberNavController())
    }
}