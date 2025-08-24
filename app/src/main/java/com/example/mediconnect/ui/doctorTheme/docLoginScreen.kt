package com.example.mediconnect.ui.doctorTheme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediconnect.AppScreen
import com.example.mediconnect.R
import com.example.mediconnect.ui.LoginChooseButtonBar
import com.example.mediconnect.ui.theme.ArimaTypography
import com.example.mediconnect.ui.theme.BalooTypography


@Composable
fun DoctorLoginScreen(
    modifier: Modifier = Modifier,
    onForgetPwdClick: () -> Unit,
    onLoginClick: () -> Unit,
    onTurnUsersClick: () -> Unit,
    chooseBar: AppScreen,
    id: String,
    onChangeId: (String) -> Unit,
    pwd: String,
    onChangePwd: (String) -> Unit,
    loginError: Boolean,
    onChangeLoginError: (Boolean) -> Unit
) {
    var pwdVisible by remember { mutableStateOf(false) }

    if (loginError) {
        AlertDialog(
            onDismissRequest = { onChangeLoginError(false) },
            title = { Text("Login Error", style = ArimaTypography.displayLarge) },
            text = { Text("Invalid ID or password. Please try again.") },
            confirmButton = {
                TextButton(
                    onClick = { onChangeLoginError(false) }
                ) {
                    Text("OK")
                }
            }
        )
    }
    Image(
        painter = painterResource(R.drawable.loginpage2),
        contentDescription = "LoginPageBackground",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = BalooTypography.titleMedium,
            modifier = Modifier
                .padding(top = 50.dp)
        )
        Spacer(
            modifier = Modifier
                .height(220.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0x4D00C8B3)
            )

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    style = ArimaTypography.displayLarge,
                    modifier = Modifier
                        .padding(bottom = 30.dp, top = 50.dp)
                )

                EditDocIdTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                        .height(65.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(35.dp)
                        ),
                    value = id,
                    onChangeValue = onChangeId
                )

                Spacer(
                    modifier = Modifier
                        .height(35.dp)
                )

                EditDocPwdTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                        .height(65.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(35.dp)
                        ),
                    value = pwd,
                    onChangeValue = onChangePwd,
                    onClick = {pwdVisible = !pwdVisible},
                    pwdVisible = pwdVisible
                )

                ForgotDocPwdButton(
                    onClick = onForgetPwdClick
                )

                LoginDocButton(
                    onClick = onLoginClick,
                    id = id,
                    pwd = pwd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 70.dp)
                        .height(40.dp)
                )


                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                )

            }

        }

        Spacer(
            modifier = Modifier
                .height(15.dp)
        )


        LoginChooseButtonBar(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            chooseBar = chooseBar,
            onTurnPageClick = onTurnUsersClick
        )

    }
}

@Composable
fun LoginDocButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    id: String,
    pwd: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = id.isNotEmpty() && pwd.isNotEmpty() && pwd.length >= 6,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(R.string.login),
            style = ArimaTypography.displayMedium
        )
    }
}

@Composable
fun ForgotDocPwdButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = stringResource(R.string.forgot_pwd),
            style = ArimaTypography.displaySmall,
            color = Color.Blue
        )
    }

}

@Composable
fun EditDocIdTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit
) {

    Box(
        modifier = modifier

    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 15.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.email),
                contentDescription = "Email Picture",
                modifier = Modifier
                    .size(30.dp),
            )

            TextField(
                placeholder = {Text("ID", color = Color.Gray)},
                value = value,
                onValueChange = onChangeValue,
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black

                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(5.dp)
            )

        }
    }
}

@Composable
fun EditDocPwdTextField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit,
    onClick: () -> Unit,
    pwdVisible: Boolean
) {
    Box(
        modifier = modifier

    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 15.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.password),
                contentDescription = "Email Picture",
                modifier = Modifier
                    .size(30.dp),
            )

            TextField(
                placeholder = {Text("Password", color = Color.Gray)},
                value = value,
                onValueChange = onChangeValue,
                singleLine = true,
                visualTransformation = if (pwdVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,    // 聚焦时底线
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = Color.Black

                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)


            )

            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .size(25.dp)
            ) {
                Icon(
                    imageVector = if(pwdVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (pwdVisible) "Hide password" else "Show password"
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DoctorLoginScreenPreview() {
    DoctorLoginScreen(
        onForgetPwdClick = {},
        onLoginClick = {},
        chooseBar = AppScreen.UserLogin,
        onTurnUsersClick = {},
        id = "",
        onChangeId = {},
        pwd = "",
        onChangePwd = {},
        loginError = false,
        onChangeLoginError = {}
    )
}