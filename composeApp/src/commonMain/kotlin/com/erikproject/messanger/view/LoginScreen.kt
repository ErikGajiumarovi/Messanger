package com.erikproject.messanger.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Label
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.erikproject.messanger.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onBack: () -> Unit,
    vm: LoginViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SomeText")
        Button(
            onClick = { vm.login("test","test") }
        ) {
            Text("click for test")
        }
        Button(
            onClick = onBack,
            shape = RoundedCornerShape(14.dp), // iOS-style rounded corners
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Go Back")
        }
    }
}