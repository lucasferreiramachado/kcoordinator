package com.lucasferreiramachado.kcoordinator.example.multiplatform.feature.auth.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LoginView(
    state: LoginViewState,
    onEvent: (LoginViewEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
    ) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(40.dp)) {

            Text(text = "Hello,\nwelcome to the login page", fontSize = 25.sp, color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            OutlinedTextField(value = state.username, onValueChange = {
                onEvent(LoginViewEvent.UsernameChanged(it))
            },
                isError =  state.usernameError != null,
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "person")
                },
                label = {
                    Text(text = "username")
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp)
            )
            if (state.usernameError != null) {
                Text(
                    modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
                    color = Color.Red,
                    text = state.usernameError!!
                )
            }

            val showPassword = state.isVisiblePassword
            val passwordVisualTransformation = if (showPassword)  VisualTransformation.None else PasswordVisualTransformation()

            OutlinedTextField(value = state.password, onValueChange = {
                onEvent(LoginViewEvent.PasswordChanged(it))
            },
                isError =  state.passwordError != null,
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = "password")
                },
                label = {
                    Text(text = "password")
                },
                trailingIcon = {
                    val image = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (showPassword) "Hide password" else "Show password"

                    Icon(
                        imageVector = image,
                        contentDescription = description,
                        modifier = Modifier.clickable {
                            onEvent(LoginViewEvent.PasswordVisibilityChanged)
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 20.dp, 0.dp, 0.dp),
                visualTransformation = passwordVisualTransformation
            )
            if (state.passwordError != null) {
                Text(
                    modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
                    color = Color.Red,
                    text = state.passwordError!!
                )
            }

            OutlinedButton(onClick = {
                onEvent(
                    LoginViewEvent.SignInButtonPressed
                )},
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                ),
                enabled = state.signInButtonEnabled,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp)) {
                Text(text = "Sign in",
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }

        Text(text = "KCoordinator Navigation Compose Sample",
            modifier = Modifier.fillMaxWidth().padding(16.dp).align(Alignment.BottomCenter),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}