package com.dilbargambarova.devjoint.devjoint_authflowapp.presentation

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Qeydiyyat", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier
            .height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = null },
            label = { Text("E-poçt") },
            singleLine = true,
            isError = emailError != null,
            supportingText = {

                emailError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier
            .height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = null },
            label = { Text("Şifrə") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            supportingText = {

                passwordError?.let {
                    Text(text = it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier
            .height(8.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it; confirmPasswordError = null },
            label = { Text("Şifrənin Təkrarı") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            isError = confirmPasswordError != null,
            supportingText = {

                confirmPasswordError?.let {
                    Text(text = it, color = MaterialTheme
                    .colorScheme.error) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                var isValid = true
                if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "Düzgün e-poçt ünvanı daxil edin"
                    isValid = false
                }
                if (password.length < 6) {
                    passwordError = "Şifrə minimum 6 simvol olmalıdır"
                    isValid = false
                }
                if (password != confirmPassword) {
                    confirmPasswordError = "Şifrələr uyğun gəlmir"
                    isValid = false
                }

                if (isValid) {
                    println("Qeydiyyat uğurludur!")
                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = "Qeydiyyatdan keç", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text("Artıq hesabın var? Daxil ol")
        }
    }
}