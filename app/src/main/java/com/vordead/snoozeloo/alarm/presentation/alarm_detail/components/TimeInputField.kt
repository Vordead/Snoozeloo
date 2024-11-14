package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.forEachChange
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.R
import com.vordead.snoozeloo.ui.theme.SnoozelooTheme


@Composable
fun AlarmTimeInput(
    modifier: Modifier = Modifier,
    onTimeChange: (String, String) -> Unit
) {
    val hourTextInputState = rememberTextFieldState("00")
    val minuteTextInputState = rememberTextFieldState("00")
    val focus = LocalFocusManager.current

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
                modifier = Modifier
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_colon) ,
                contentDescription = "Colon",
                tint = Color(0xFF858585),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            TimeInputField(
                state = minuteTextInputState,
                modifier = Modifier
            )
        }
    }
}


@Composable
fun TimeInputField(
    state: TextFieldState,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        state = state,
        modifier = modifier,
        inputTransformation = TwoDigitsOnlyTransformation,
        textStyle = MaterialTheme.typography.displayLarge.copy(
            fontSize = 52.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color(0xFF858585)
        ),
        cursorBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF4664FF),
                Color(0xFF4664FF)
            )
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextField ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
                modifier = Modifier.width(128.dp) // Adjust the width as needed
            ) {
                Box(Modifier.padding(horizontal = 29.dp, vertical = 16.dp)) {
                    innerTextField()
                }
            }
        }
    )


}

object TwoDigitsOnlyTransformation : InputTransformation {
    @OptIn(ExperimentalFoundationApi::class)
    override fun TextFieldBuffer.transformInput() {
        if (asCharSequence().length > 2) {
            replace(0, asCharSequence().length, asCharSequence().subSequence(0, 2))
        }
        changes.forEachChange { range, _ ->
            Log.d("DigitsOnlyTransformation", changes.toString())
            if (!range.collapsed) {
                val charInput = asCharSequence()[range.min]
                if (!charInput.isDigit()) {
                    replace(range.min, range.max, "")
                }
            }
        }
    }
}


@Preview
@Composable
fun TimeInputFieldPreview() {
    val state = rememberTextFieldState()
    SnoozelooTheme {
        TimeInputField(
            modifier = Modifier.systemBarsPadding(),
            state = state,
        )
    }
}

@Preview
@Composable
fun AlarmTimeInputPreview() {
    AlarmTimeInput(onTimeChange = { _, _ -> }, modifier = Modifier.systemBarsPadding())
}