package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.substring
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.ui.theme.SnoozelooTheme


@Composable
fun AlarmTimeInput(
    modifier: Modifier = Modifier,
    onTimeChange: (String, String) -> Unit
) {
    var hour by remember { mutableStateOf("") }
    var minute by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

    }
}


@Composable
fun TimeInputField(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        state = state ,
        modifier = modifier,
        inputTransformation = DigitsOnlyTransformation,
        textStyle = LocalTextStyle.current.copy(fontSize = 20.sp),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextField ->
            Row(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .height(48.dp)
                    .width(48.dp)
            ) {
                innerTextField()
            }
        }
    )
}

object DigitsOnlyTransformation : InputTransformation {
    @OptIn(ExperimentalFoundationApi::class)
    override fun TextFieldBuffer.transformInput() {
        changes.forEachChange { range, _ ->
            Log.d("DigitsOnlyTransformation", changes.toString())
            val ss = asCharSequence().substring(range)
            if (!range.collapsed) {
                val newText = ss.filter { it.isDigit() }.trimEnd()
                if (newText != ss) {
                    replace(range.min, range.max, newText)
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
            placeholder = "00"
        )
    }
}

@Preview
@Composable
fun AlarmTimeInputPreview() {
    AlarmTimeInput(onTimeChange = { _, _ -> })
}