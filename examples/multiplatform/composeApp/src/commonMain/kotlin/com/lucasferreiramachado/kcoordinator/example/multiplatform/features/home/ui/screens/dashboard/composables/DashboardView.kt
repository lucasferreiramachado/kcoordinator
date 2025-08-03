package com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.DashboardUiEvent
import com.lucasferreiramachado.kcoordinator.example.multiplatform.features.home.ui.screens.dashboard.DashboardUiState

@Composable
fun DashboardView(
    state: DashboardUiState,
    onEvent: (DashboardUiEvent) -> Unit
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

            Text(text = "Hello,\nwelcome to the dashboard page", fontSize = 25.sp, color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth().padding(0.dp, 50.dp, 0.dp, 0.dp)
            )

            OutlinedButton(onClick = {
                onEvent(
                    DashboardUiEvent.BackButtonPressed
                )},
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth().padding(0.dp, 25.dp, 0.dp, 0.dp)) {
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