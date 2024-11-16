package com.vordead.snoozeloo.alarm.presentation.alarm_detail.util

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.forEachChange
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.placeCursorAtEnd
import androidx.compose.foundation.text.input.then
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.KeyboardType
import kotlin.text.isDigit

@OptIn(ExperimentalFoundationApi::class)
@Stable
fun InputTransformation.limitedInput(maxValue: Int): InputTransformation =
    this.then(MaxValueDigitInputTransformation(maxValue))
        .then(InputTransformation.maxLength(2))


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
