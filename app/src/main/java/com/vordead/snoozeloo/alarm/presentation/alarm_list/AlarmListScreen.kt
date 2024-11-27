package com.vordead.snoozeloo.alarm.presentation.alarm_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AddAlarmFab
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AlarmList
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.AppBar
import com.vordead.snoozeloo.alarm.presentation.alarm_list.components.EmptyData
import com.vordead.snoozeloo.alarm.presentation.models.AlarmUi
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground
import java.time.LocalDateTime

@Composable
fun AlarmListScreen(
    state: AlarmListState,
    onAction: (AlarmListAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            AddAlarmFab(
                onClick = {
                    onAction(AlarmListAction.onCreateAlarmClick)
                },
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .consumeWindowInsets(paddingValues),
            ) {
                AppBar(
                    title = "Your Alarms",
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .shadow(
                            elevation = 10.dp,
                            spotColor = Color(0xFFF6F6F6),
                            clip = true,
                            ambientColor = Color(0xFFF6F6F6),
                            shape = CircleShape
                        )
                )
                when (val uiState = state.uiState) {
                    is AlarmListUiState.Loading -> {
                        // Show loading indicator
                    }

                    is AlarmListUiState.Success -> {
                        if (uiState.alarms.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .navigationBarsPadding()
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                EmptyData(
                                    modifier = Modifier
                                        .fillMaxSize()
                                )
                            }
                        } else {
                            Box {
                                AlarmList(
                                    alarms = uiState.alarms,
                                    onAlarmClick = { alarm ->
                                        onAction(AlarmListAction.onAlarmClick(alarm.id!!))
                                    },
                                    onAlarmSwitchClick = { alarm, isChecked ->
                                        onAction(
                                            AlarmListAction.onAlarmSwitchClick(
                                                alarm.id!!,
                                                isChecked
                                            )
                                        )
                                    },
                                    modifier = Modifier.fillMaxSize()
                                )
                                LandingHeader()
                            }
                        }
                    }

                    is AlarmListUiState.Error -> {
                        // Show error message
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun AlarmListScreenPreview() {
    SnoozelooBackground {
        AlarmListScreen(
            state = AlarmListState(
                uiState = AlarmListUiState.Success(
                    alarms = listOf(
                        AlarmUi(
                            id = 1,
                            title = "Alarm 1",
                            remainingTime = "30min",
                            repeatDays = emptyList(),
                            dateTime = LocalDateTime.now(),
                            isEnabled = true
                        ),
                    )
                )
            ),
            onAction = {}
        )
    }
}

@Composable
fun LandingHeader() {
    Box(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxWidth()
            .height(40.dp)
            .background(
                brush = Brush.linearGradient(
                    0.0f to MaterialTheme.colorScheme.background,
                    1.0f to Color.Transparent,
                    start = Offset(0.0f, 0.0f),
                    end = Offset(0.0f, 100.0f)
                )
            )
    )
}

@Preview
@Composable
private fun LandingPreview() {
    SnoozelooBackground {
        LandingHeader()
    }
}