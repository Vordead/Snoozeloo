package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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

@Composable
fun AlarmTimeInput(
    modifier: Modifier = Modifier,
    onTimeChange: (String, String) -> Unit
) {
    val hourTextInputState = rememberTextFieldState("")
    val minuteTextInputState = rememberTextFieldState("")

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TimeInputField(
                state = hourTextInputState,
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
                state = minuteTextInputState,
                inputTransformation = InputTransformation.minuteInput(),
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun AlarmTimeInputPreview() {
    AlarmTimeInput(onTimeChange = { _, _ -> }, modifier = Modifier.systemBarsPadding())
}