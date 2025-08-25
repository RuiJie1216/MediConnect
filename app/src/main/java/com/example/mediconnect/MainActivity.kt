package com.example.mediconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mediconnect.data.AccountData
import com.example.mediconnect.data.DocUiState
import com.example.mediconnect.data.UserUiState
import com.example.mediconnect.ui.ResetPwdScreen
import com.example.mediconnect.ui.doctorTheme.DocMain
import com.example.mediconnect.ui.doctorTheme.DocViewModel
import com.example.mediconnect.ui.doctorTheme.DoctorHomeScreen
import com.example.mediconnect.ui.doctorTheme.DoctorLoginScreen
import com.example.mediconnect.ui.doctorTheme.PatientsScreen
import com.example.mediconnect.ui.theme.MediConnectTheme
import com.example.mediconnect.ui.userTheme.UserLoginScreen
import com.example.mediconnect.ui.userTheme.UserSignUpScreen
import com.example.mediconnect.ui.userTheme.UserViewModel


enum class AppScreen(val hasTopBar: Boolean, val hasReturn: Boolean) {
    DoctorLogin(false, false),
    UserLogin(false,false),
    UserSignUp(true, true),
    ForgotPwd(true, true),
    UserMain(true, false),
    DoctorMain(true, false),
    DoctorPatient(true, true)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(
    currentScreen: AppScreen,
    docViewModel: DocViewModel,
    userViewModel: UserViewModel,
    docUiState: DocUiState,
    userUiState: UserUiState,
    navigateBack: (AppScreen) -> Unit
) {
    when(currentScreen) {
        AppScreen.DoctorMain -> {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
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
        AppScreen.DoctorPatient -> {
            LaunchedEffect(Unit) {
                docViewModel.setSearchQuery("")
            }

            TopAppBar(
                title = {
                    TextField(
                        value = docUiState.searchQuery,
                        onValueChange = { docViewModel.setSearchQuery(it) },
                        placeholder = { Text("Search patients...") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFF29E6D2),
                            unfocusedContainerColor = Color(0xFF29E6D2),
                            disabledContainerColor = Color(0xFF29E6D2),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {navigateBack(AppScreen.DoctorMain)}
                    ) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF29E6D2)
                )
            )
        }
        AppScreen.UserSignUp -> {

        }
        AppScreen.ForgotPwd -> {

        }
        AppScreen.UserMain -> {

        }
        AppScreen.UserLogin -> {

        }
        AppScreen.DoctorLogin -> {

        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediConnectTheme {
                MainPageApp()
            }
        }
    }
}

@Composable
fun MainPageApp(
    modifier: Modifier = Modifier,
    docViewModel: DocViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val docUiState by docViewModel.docUiState.collectAsState()
    val userUiState by userViewModel.userUiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.UserLogin.name
    )

    Scaffold(
        topBar = {
            if (currentScreen.hasTopBar)
                TopBarScreen(
                    currentScreen = currentScreen,
                    docViewModel = docViewModel,
                    userViewModel = userViewModel,
                    docUiState = docUiState,
                    userUiState = userUiState,
                    navigateBack = {if (currentScreen.hasReturn) navController.navigate(it.name) }
                )
        }
    ) { innerPadding ->


        NavHost(
            navController = navController,
            startDestination = AppScreen.UserLogin.name,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(route = AppScreen.UserLogin.name) {
                val context = LocalContext.current
                var ic by remember { mutableStateOf("") }
                var pwd by remember { mutableStateOf("") }
                UserLoginScreen(
                    modifier = Modifier
                        .fillMaxHeight(),
                    chooseBar = currentScreen,
                    ic = ic,
                    onChangeIc = {ic = it},
                    pwd = pwd,
                    onchangePwd = {pwd = it},
                    onForgetPwdClick = {
                        navController.navigate(AppScreen.ForgotPwd.name)
                    },
                    onLoginClick = {
                        if (checkLogin(icId = ic, pwd = pwd, person = "user")) {
                            navController.navigate(AppScreen.UserMain.name)
                            userViewModel.setIC(ic)
                            userViewModel.setPwd(pwd)
                        } else {
                            ic = ""
                            pwd = ""
                            Toast.makeText(context, "Invalid Account or password.\nPlease input again.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(AppScreen.UserSignUp.name)
                    },
                    onTurnDoctorClick = {
                        navController.navigate(AppScreen.DoctorLogin.name)
                    }
                )
            }

            composable(route = AppScreen.DoctorLogin.name) {
                val context = LocalContext.current
                var id by remember { mutableStateOf("") }
                var pwd by remember { mutableStateOf("") }
                DoctorLoginScreen(
                    modifier = Modifier
                        .fillMaxHeight(),
                    chooseBar = currentScreen,
                    id = id,
                    onChangeId = {id = it},
                    pwd = pwd,
                    onChangePwd = {pwd = it},
                    onForgetPwdClick = {
                        navController.navigate(AppScreen.ForgotPwd.name)
                    },
                    onLoginClick = {
                        if (checkLogin(icId = id, pwd = pwd, person = "doctor")) {
                            navController.navigate(AppScreen.DoctorMain.name)
                            docViewModel.setID(id)
                            docViewModel.setPwd(pwd)
                        } else {
                            id = ""
                            pwd = ""
                            Toast.makeText(context, "Invalid Account or password.\nPlease input again.", Toast.LENGTH_SHORT).show()
                        }
                    },
                    onTurnUsersClick = {
                        navController.navigate(AppScreen.UserLogin.name)
                    }
                )
            }

            composable(route = AppScreen.ForgotPwd.name) {
                ResetPwdScreen(
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }

            composable(route = AppScreen.UserSignUp.name) {
                UserSignUpScreen(
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }

            composable(route = AppScreen.DoctorMain.name) {
                DoctorHomeScreen(
                    modifier = Modifier
                        .fillMaxHeight(),
                    onAppointmentClick = {},
                    onProfileClick = {},
                    onPatientClick = {
                        navController.navigate(AppScreen.DoctorPatient.name)
                    }
                )
            }

            composable(route = AppScreen.DoctorPatient.name) {
                PatientsScreen(
                    modifier = Modifier
                        .fillMaxHeight(),
                    docUiState = docUiState,
                    onAddClick = {},
                    onBackClick = {},
                    onPatientDetailClick = {},
                )
            }
        }

    }
}




private fun checkLogin(
    modifier: Modifier = Modifier,
    icId: String,
    pwd: String,
    person: String
): Boolean {
    var checkAccountValid = false

    when (person) {
        "user" -> {
            val checkUser = AccountData.userAccount.find { it.ic == icId }

            if (checkUser != null) {
                if(pwd == checkUser.pwd) {
                    checkAccountValid = true
                }
            }
        }
        "doctor" -> {
            val checkDoctor = AccountData.docAccount.find { it.id == icId }

            if (checkDoctor != null) {
                if(pwd == checkDoctor.pwd) {
                    checkAccountValid = true
                }
            }
        }

    }

    return checkAccountValid
}

