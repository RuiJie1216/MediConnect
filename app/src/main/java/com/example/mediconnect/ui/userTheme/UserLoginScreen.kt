package com.example.mediconnect.ui.userTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.example.mediconnect.ui.LoginChooseButtonBar
import com.example.mediconnect.ui.theme.MediConnectTheme


@Composable
fun UserLoginScreen(
    modifier: Modifier = Modifier,
    onForgetPwdClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onTurnDoctorClick: () -> Unit,
    chooseBar: AppScreen,
    ic: String,
    onChangeIc: (String) -> Unit,
    pwd: String,
    onchangePwd: (String) -> Unit
) {
    var pwdVisible by remember { mutableStateOf(false) }

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
                        .padding(bottom = 20.dp, top = 20.dp)
                )

                EditUserIcTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                        .height(65.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(35.dp)
                        ),
                    value = ic,
                    onChangeValue = onChangeIc
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                EditUserPwdTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp)
                        .height(65.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(35.dp)
                        ),
                    value = pwd,
                    onChangeValue = onchangePwd,
                    onClick = {pwdVisible = !pwdVisible},
                    pwdVisible = pwdVisible
                )

                ForgotUserPwdButton(
                    onClick = onForgetPwdClick
                )

                LoginUserButton(
                    onClick = onLoginClick,
                    ic = ic,
                    pwd = pwd,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 70.dp)
                        .height(40.dp)
                )

                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                )

                Text(
                    text = stringResource(R.string.sign_up_message),
                    style = ArimaTypography.displaySmall,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )

                SignInUserButton(
                    onClick = onSignUpClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 70.dp)
                        .height(40.dp)
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
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
            onTurnPageClick = onTurnDoctorClick
        )

    }
}



@Composable
fun SignInUserButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(
            text = stringResource(R.string.sign_up),
            style = ArimaTypography.displayMedium,
        )
    }

}

@Composable
fun LoginUserButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    ic: String,
    pwd: String
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = ic.isNotEmpty() && pwd.isNotEmpty() && pwd.length >= 6,
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
fun ForgotUserPwdButton(
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
fun EditUserIcTextField(
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
                placeholder = {Text("IC / Passport No", color = Color.Gray)},
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
fun EditUserPwdTextField(
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
fun UserLoginPreview() {
    MediConnectTheme {
        UserLoginScreen(
            onForgetPwdClick = {},
            onLoginClick = {},
            onSignUpClick = {},
            chooseBar = AppScreen.UserLogin,
            onTurnDoctorClick = {},
            ic = "",
            onChangeIc = {},
            pwd = "",
            onchangePwd = {}
        )
    }
}