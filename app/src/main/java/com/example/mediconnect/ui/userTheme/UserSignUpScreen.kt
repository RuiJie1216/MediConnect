package com.example.mediconnect.ui.userTheme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediconnect.R
import com.example.mediconnect.ui.theme.MediConnectTheme


@Composable
fun UserSignUpScreen1(
    modifier: Modifier = Modifier,
    existPatient: Boolean?,
    onChangeExistPatient: (Boolean) -> Unit,
    ic: String,
    onChangeIc: (String) -> Unit,
    name: String,
    onChangeName: (String) -> Unit,
    read: Boolean,
    onChangeRead: (Boolean) -> Unit,
    onNextClick: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.about_me),
                fontSize = 16.sp,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.Black
            )
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        EditExistPatient(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(MaterialTheme.colorScheme.background),
            selectedExist = existPatient,
            selectedExistOption = onChangeExistPatient
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        EditIcField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(MaterialTheme.colorScheme.background),
            value = ic,
            onChangeValue = onChangeIc
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        EditNameField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(MaterialTheme.colorScheme.background),
            value = name,
            onChangeValue = onChangeName
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        EditQuestionField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .background(MaterialTheme.colorScheme.background),
            selectedYesNo = read,
            selectedYesNoOption = {onChangeRead(!read)}
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Terms & Conditions",
                color = if (isSystemInDarkTheme()) Color(0xFF6F97FF) else Color(0xFF225DF2)
            )
        }

        Spacer(
            modifier = Modifier
                .height(70.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onNextClick,
                enabled = existPatient != null && ic.isNotEmpty() && name.isNotEmpty() && read
            ) {
                Text(
                    text = "Next"
                )
            }
        }



    }

}

@Composable
fun EditExistPatient(
    modifier: Modifier = Modifier,
    selectedExist: Boolean?,
    selectedExistOption: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.exist_patient),
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.Gray
        )

        Row(
            modifier = Modifier
                .clickable{selectedExistOption(true)}
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedExist == true,
                onClick = {selectedExistOption(true)}
            )
            Text(
                text = "Yes",
                color = if(isSystemInDarkTheme()) Color.White else Color.Black
            )

            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )

            RadioButton(
                selected = selectedExist == false,
                onClick = {selectedExistOption(false)}
            )
            Text(
                text = "No",
                color = if(isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun EditIcField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.ic_passport),
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.Gray
        )

        TextField(
            value = value,
            onValueChange = onChangeValue,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        )
    }
}

@Composable
fun EditNameField(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.full_name),
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.Gray
        )

        TextField(
            value = value,
            onValueChange = onChangeValue,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = if (isSystemInDarkTheme()) Color.White else Color.Black
            )
        )
    }
}

@Composable
fun EditQuestionField(
    modifier: Modifier = Modifier,
    selectedYesNo: Boolean,
    selectedYesNoOption: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.question1),
            color = if(isSystemInDarkTheme()) Color.LightGray else Color.Gray
        )

        Row(
            modifier = Modifier
                .clickable{selectedYesNoOption}
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedYesNo,
                onClick = selectedYesNoOption
            )
            Text(
                text = "Yes",
                color = if(isSystemInDarkTheme()) Color.White else Color.Black
            )
        }

    }

}

@Composable
fun UserSignUpScreen2(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Button(
        onClick = onBackClick
    ) { }

}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SignUpPreviewDark() {
    MediConnectTheme {
        UserSignUpScreen1(
            modifier = Modifier
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.background),
            existPatient = null,
            onChangeExistPatient = {},
            ic = "",
            onChangeIc = {},
            name = "",
            onChangeName = {},
            read = false,
            onChangeRead = {},
            onNextClick = {}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SignUpPreviewBright() {
    MediConnectTheme {
        UserSignUpScreen1(
            modifier = Modifier
                .fillMaxHeight(),
            existPatient = null,
            onChangeExistPatient = {},
            ic = "",
            onChangeIc = {},
            name = "",
            onChangeName = {},
            read = false,
            onChangeRead = {},
            onNextClick = {}
        )
    }
}