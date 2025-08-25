package com.example.mediconnect.ui.doctorTheme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mediconnect.data.DocUiState
import com.example.mediconnect.ui.theme.MediConnectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    docUiState: DocUiState,
    onPatientDetailClick: () -> Unit
) {
    data class Patient(
        val name: String,
        val condition: String
    )

    val allPatients = listOf(
        Patient("Albert Wong Jim Yam", "Fever, Cough"),
        Patient("Albert Yong Hong Lin", "Diabetes, High Cholesterol"),
        Patient("Amy Lee Kai Xin", "Early Hypertension"),
        Patient("Bella Chong Zhi Hong", "Vomiting, Diarrhea"),
        Patient("Chan Xing Yee", "Back Pain / Shoulder Pain"),
        Patient("Chloe Tan Wen Jie", "Headache, Insomnia / Sleep Problems"),
        Patient("Chong Zhen Ling", "Mild Allergic Reaction"),
        Patient("Chong Zhen Yang", "Mild Allergic Reaction"),
    )

    Box(
        modifier = modifier
    ) {

        // 🔹 主体区域：列表 + A-Z
        Row(modifier = Modifier.fillMaxSize()) {
            val filteredPatients = allPatients.filter {
                it.name.contains(docUiState.searchQuery, ignoreCase = true)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                items(filteredPatients) { patient ->
                    PatientItem(
                        name = patient.name,
                        condition = patient.condition,
                        onClick = onPatientDetailClick
                    )
                }
            }

            // 🔹 右边 A-Z 快速导航条
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(20.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ('A'..'Z').forEach { letter ->
                    Text(
                        text = letter.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }

}

@Composable
fun PatientItem(
    name: String,
    condition: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Patient Icon",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Text(text = condition, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PatientPreview() {
    MediConnectTheme {
        PatientsScreen(
            onPatientDetailClick = {},
            onBackClick = {},
            onAddClick = {},
            docUiState = DocUiState("", "", "")
        )
    }
}