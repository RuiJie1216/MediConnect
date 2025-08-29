package com.example.mediconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.example.mediconnect.data.AccountData
import com.example.mediconnect.data.DocUiState
import com.example.mediconnect.data.UserUiState
import com.example.mediconnect.ui.ResetPwdScreen
import com.example.mediconnect.ui.ScreenViewModel
import com.example.mediconnect.ui.doctorTheme.DocMain
import com.example.mediconnect.ui.doctorTheme.DocViewModel
import com.example.mediconnect.ui.doctorTheme.DoctorHomeScreen
import com.example.mediconnect.ui.doctorTheme.DoctorLoginScreen
import com.example.mediconnect.ui.doctorTheme.PatientsScreen
import com.example.mediconnect.ui.doctorTheme.SearchState
import com.example.mediconnect.ui.theme.MediConnectTheme
import com.example.mediconnect.ui.userTheme.UserLoginScreen
import com.example.mediconnect.ui.userTheme.UserSignUpScreen1
import com.example.mediconnect.ui.userTheme.UserSignUpScreen2
import com.example.mediconnect.ui.userTheme.UserViewModel


enum class AppScreen(
    val hasReturn: Boolean
) {
    //Login Page
    DoctorLogin(false),
    UserLogin(false),
    UserSignUp(true),
    ForgotPwd(true),

    //User
    UserSystem(false),
    UserMain(false),

    //Doctor
    DoctorSystem(false),
    DoctorMain(false),
    DoctorPatient(true)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarScreen(
    currentScreen: AppScreen,
    searchState: SearchState,
    screen: Int,
    navigate: (AppScreen) -> Unit
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
            TopAppBar(
                title = {
                    TextField(
                        value = searchState.searchQuery,
                        onValueChange = { searchState.searchQuery = it },
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
                        onClick = {navigate(AppScreen.DoctorMain)}
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
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.sign_up_top),
                        fontSize = 24.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {navigate(AppScreen.UserLogin)}
                    ) {
                        Icon(
                            Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = "Back to login page",
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)

                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF29E6D2)
                )
            )
        }
        AppScreen.ForgotPwd -> {

        }
        AppScreen.UserMain -> {

        }
        else -> {}
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
    screenVM: ScreenViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val docUiState by docViewModel.docUiState.collectAsState()
    val userUiState by userViewModel.userUiState.collectAsState()
    val screen by screenVM.step.collectAsState()
    val searchState = remember { SearchState() }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.entries.find { it.name == backStackEntry?.destination?.route } ?: AppScreen.UserLogin


    Scaffold(
        topBar = {
                TopBarScreen(
                    currentScreen = currentScreen,
                    screen = screen,
                    searchState = searchState,
                    navigate = {if (currentScreen.hasReturn) navController.navigate(it.name) }
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
                var ic by remember { mutableStateOf("") }
                var pwd by remember { mutableStateOf("") }
                var loginError by remember { mutableStateOf(false) }
                if (loginError) {
                    ErrorLoginMessage(
                        showMessage = true,
                        onDismiss = {loginError = false}
                    )
                }
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
                            navController.navigate(AppScreen.UserSystem.name)
                            userViewModel.setIC(ic)
                            userViewModel.setPwd(pwd)
                        } else {
                            ic = ""
                            pwd = ""
                            loginError = true
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
                var id by remember { mutableStateOf("") }
                var pwd by remember { mutableStateOf("") }
                var loginError by remember { mutableStateOf(false) }
                if (loginError) {
                    ErrorLoginMessage(
                        showMessage = true,
                        onDismiss = {loginError = false}
                    )
                }
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
                            navController.navigate(AppScreen.DoctorSystem.name)
                            docViewModel.setID(id)
                            docViewModel.setPwd(pwd)
                        } else {
                            id = ""
                            pwd = ""
                            loginError = true
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
                LaunchedEffect(Unit) {
                    screenVM.reset()
                }
                var existPatient by remember { mutableStateOf<Boolean?>(null) }
                var ic by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("") }
                var read by remember { mutableStateOf(false) }

                when (screen) {
                    1 -> UserSignUpScreen1(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.background),
                        existPatient = existPatient,
                        onChangeExistPatient = {existPatient = it},
                        ic = ic,
                        onChangeIc = {ic = it},
                        name = name,
                        onChangeName = {name = it},
                        read = read,
                        onChangeRead = {read = it},
                        onNextClick = {screenVM.setStep(2)}
                    )
                    2 -> UserSignUpScreen2(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.background),
                        onBackClick = {screenVM.setStep(1)}
                    )
                }
            }

            navigation(
                startDestination = AppScreen.UserMain.name,
                route = AppScreen.UserSystem.name
            ) {
                composable(route = AppScreen.UserMain.name) {

                }
            }

            navigation(
                startDestination = AppScreen.DoctorMain.name,
                route = AppScreen.DoctorSystem.name
            ) {
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
                        onAddClick = {},
                        onBackClick = {},
                        searchState = searchState,
                        onPatientDetailClick = {},
                    )
                }
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

@Composable
private fun ErrorLoginMessage(
    showMessage: Boolean,
    onDismiss: () -> Unit
) {
    if (showMessage) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Login Error") },
            text = { Text("Invalid Account or Password. Please try again.") },
            confirmButton = {
                TextButton(
                    onClick = { onDismiss() }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

