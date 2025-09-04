package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.flow.login.ui.screens.forgotpassword.ForgotPasswordEvent
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.auth.ui.screens.forgotpassword.ForgotPasswordUiState


@Composable
fun ForgotPasswordView(
    state: ForgotPasswordUiState,
    onEvent: (ForgotPasswordEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .background(MaterialTheme.colorScheme.onPrimary),
    ) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(40.dp)) {

            Text(text = "Hello,\nwelcome to the forgot password page", fontSize = 25.sp, color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            OutlinedTextField(value = state.username, onValueChange = {
                onEvent(ForgotPasswordEvent.UsernameChanged(it))
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

            OutlinedButton(onClick = {
                onEvent(
                    ForgotPasswordEvent.ResetButtonPressed
                )},
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                ),
                enabled = state.resetButtonEnabled,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp)) {
                Text(text = "Reset password",
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