package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components.time_input

import android.R.attr.bottom
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vordead.snoozeloo.R
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.util.hourInput
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.util.minuteInput
import com.vordead.snoozeloo.alarm.presentation.models.calculateRemainingTime

@Composable
fun AlarmTimeInput(
    state: TimeInputState,
    modifier: Modifier = Modifier,
) {

    var shouldShowTimeLeft by remember { mutableStateOf(false) }

    LaunchedEffect(state.hourState.text, state.minuteState.text) {
        shouldShowTimeLeft =
            state.hourState.text.isNotEmpty() && state.minuteState.text.isNotEmpty()
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimeInputField(
                    state = state.hourState,
                    inputTransformation = InputTransformation.hourInput(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                )
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_colon),
                    contentDescription = "Colon",
                    tint = Color(0xFF858585),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                TimeInputField(
                    state = state.minuteState,
                    inputTransformation = InputTransformation.minuteInput(),
                    modifier = Modifier
                )
            }

            AnimatedVisibility(
                visible = shouldShowTimeLeft,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    "Alarm in 8h 13min",
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun AlarmTimeInputPreview() {
    AlarmTimeInput(
        state = rememberTimeInputState(), modifier = Modifier.systemBarsPadding()
    )
}