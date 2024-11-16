package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.forEachChange
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.placeCursorAtEnd
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimeInputField(
    state: TextFieldState,
    inputTransformation: InputTransformation,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
) {

    val focus = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value

    LaunchedEffect(isFocused) {
        when {
            isFocused && state.text == "00" -> {
                state.clearText()
            }

            !isFocused && state.text.length == 1 -> {
                state.edit {
                    replace(0, 1, "0${state.text}")
                }
            }

            !isFocused && state.text == "0" -> {
                state.edit {
                    replace(0, 1, "00")
                }
            }
        }
    }

    BasicTextField(
        state = state,
        modifier = modifier,
        interactionSource = interactionSource,
        inputTransformation = inputTransformation.then(InputTransformation.maxLength(2)),
        textStyle = MaterialTheme.typography.displayLarge.copy(
            fontSize = 52.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color(0xFF858585)
        ),
        cursorBrush = Brush.linearGradient(
            colors = listOf(
                Color(0xFF4664FF),
                Color(0xFF4664FF),
            )
        ),
        keyboardOptions = keyboardOptions,
        onKeyboardAction = KeyboardActionHandler { performDefaultAction ->
            if (state.text.isEmpty()) {
                state.edit {
                    append("00")
                }
            }
            when (keyboardOptions.imeAction) {
                ImeAction.Next -> {
                    focus.moveFocus(FocusDirection.Next)
                }

                ImeAction.Done -> {
                    focus.clearFocus()
                }

                else -> performDefaultAction()
            }
        },
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = { innerTextField ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF6F6F6)),
                modifier = Modifier.width(128.dp)
            ) {
                Box(Modifier.padding(horizontal = 29.dp, vertical = 16.dp)) {
                    innerTextField()
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Stable
fun InputTransformation.limitedInput(maxValue: Int): InputTransformation =
    this.then(MaxValueDigitInputTransformation(maxValue))


@Stable
fun InputTransformation.hourInput(): InputTransformation = limitedInput(23)

@Stable
fun InputTransformation.minuteInput(): InputTransformation = limitedInput(59)


data class MaxValueDigitInputTransformation(val maxValue: Int) : InputTransformation {
    override val keyboardOptions: KeyboardOptions?
        get() = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    @OptIn(ExperimentalFoundationApi::class)
    override fun TextFieldBuffer.transformInput() {
        placeCursorAtEnd()
        changes.forEachChange { range, _ ->
            Log.d("DigitsOnlyTransformation", changes.toString())
            if (!range.collapsed) {
                val charInput = asCharSequence()[range.min]
                if (!charInput.isDigit()) {
                    replace(range.min, range.max, "")
                }
            }
            val inputText = asCharSequence().toString()
            if (inputText.isNotEmpty() && inputText.toInt() > maxValue) {
                revertAllChanges()
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
            inputTransformation = InputTransformation.hourInput(),
            state = state,
        )
    }
}

@Preview
@Composable
fun AlarmTimeInputPreview() {
    AlarmTimeInput(onTimeChange = { _, _ -> }, modifier = Modifier.systemBarsPadding())
}