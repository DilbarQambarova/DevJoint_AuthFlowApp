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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            println("Token alındı: ${(authState as AuthState.Success).token}")
            authViewModel.resetState()
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Giriş Ekranı", fontSize = 24.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier
            .height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = null },
            label = { Text("E-poçt") },
            singleLine = true,
            isError = emailError != null,
            supportingText = {

                emailError?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
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
                passwordError?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (authState is AuthState.Loading) {
            CircularProgressIndicator()
        } else {
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

                    if (isValid) {
                        authViewModel.login(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Daxil ol", fontSize = 16.sp)
            }
        }

        if (authState is AuthState.Error) {
            Spacer(modifier = Modifier
                .height(8.dp))
            Text(
                text = (authState as AuthState.Error).message,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier
            .height(16.dp))

        TextButton(
            onClick = { navController.navigate("register") },
            enabled = authState !is AuthState.Loading
        ) {
            Text("Hesabın yoxdur? Qeydiyyatdan keç")
        }
    }
}