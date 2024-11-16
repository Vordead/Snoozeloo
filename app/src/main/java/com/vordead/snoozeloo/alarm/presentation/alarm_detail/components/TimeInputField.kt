package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.alarm.presentation.alarm_detail.util.hourInput
import com.vordead.snoozeloo.ui.theme.SnoozelooTheme
val decoratedPlaceholder: @Composable ((Modifier) -> Unit)? =
    if (true) {
        @Composable { modifier ->
            Box(modifier) {
                Text(
                    text = "00",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 52.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF858585)
                    )
                )
            }
        }
    } else null
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
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
        inputTransformation = inputTransformation,
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
                Box(Modifier.padding(horizontal = 29.dp, vertical = 16.dp).fillMaxWidth()) {
                    if(state.text.isBlank()){
                        decoratedPlaceholder?.invoke(Modifier.align(Alignment.Center))
                    }
                    else innerTextField()
                }
            }
        }
    )
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