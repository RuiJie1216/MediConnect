package com.example.mediconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mediconnect.ui.ResetPwdScreen
import com.example.mediconnect.ui.doctorTheme.DocViewModel
import com.example.mediconnect.ui.doctorTheme.DoctorLoginScreen
import com.example.mediconnect.ui.theme.MediConnectTheme
import com.example.mediconnect.ui.userTheme.UserLoginScreen
import com.example.mediconnect.ui.userTheme.UserSignUpScreen
import com.example.mediconnect.ui.userTheme.UserViewModel


enum class AppScreen() {
    DoctorLogin,
    UserLogin,
    ForgotPwd,
    UserMain,
    UserSignUp,
    DoctorMain
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

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreen.valueOf(
        backStackEntry?.destination?.route ?: AppScreen.UserLogin.name
    )

    Scaffold(
    ) { innerPadding ->

        val docUiState by docViewModel.docUiState.collectAsState()
        val userUiState by userViewModel.userUiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = AppScreen.UserLogin.name,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(route = AppScreen.UserLogin.name) {
                UserLoginScreen(
                    modifier = Modifier
                        .fillMaxHeight(),
                    onForgetPwdClick = {
                        navController.navigate(AppScreen.ForgotPwd.name)
                    },
                    onLoginClick = { ic: String, pwd: String ->

                    },
                    onSignUpClick = {
                        navController.navigate(AppScreen.UserSignUp.name)
                    },
                    chooseBar = currentScreen,
                    onTurnDoctorClick = {
                        navController.navigate(AppScreen.DoctorLogin.name)
                    }
                )
            }

            composable(route = AppScreen.DoctorLogin.name) {
                DoctorLoginScreen(
                    modifier = Modifier
                        .fillMaxHeight(),
                    onForgetPwdClick = {
                        navController.navigate(AppScreen.ForgotPwd.name)
                    },
                    onLoginClick = { id: String, pwd: String ->

                    },
                    chooseBar = currentScreen,
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
        }

    }
}

