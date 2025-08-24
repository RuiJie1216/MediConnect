package com.example.mediconnect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mediconnect.data.AccountData
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
