package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.Screen2UiEvent
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.feature1.flow2.ui.screens.screen2.Screen2UiState

@Composable
fun Screen2View(
    state: Screen2UiState,
    onEvent: (Screen2UiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding(),
    ) {

        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(40.dp)) {

            Text(
                text = buildAnnotatedString {
                    append("Feature1 -> Flow2")
                },
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = buildAnnotatedString {
                    append("Hello, \nwelcome to the Screen2 page")
                },
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            OutlinedButton(
                onClick = {
                    onEvent(Screen2UiEvent.Flow1ButtonPressed)
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp),
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Text(text = "Go to Flow 1",
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

            TextButton(
                onClick = {
                  onEvent(Screen2UiEvent.BackButtonPressed)
                },
                modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp, 0.dp, 0.dp),
            ) {
                Text(text = "Go to back",
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