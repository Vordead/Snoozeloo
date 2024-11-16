package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.vordead.snoozeloo.core.presentation.SnoozelooBackground

@Composable
fun AlarmFieldChangeDialog(
    onDismiss: () -> Unit,
    onSaveClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    fieldText: String = "",
) {
    var text by rememberSaveable { mutableStateOf(fieldText) }
    Dialog(
        onDismissRequest = {onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        ),
    ) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "Alarm Name",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF0D0F19),
                    )
                )
                OutlinedTextField(
                    text,
                    onValueChange = { text = it },
                    modifier = Modifier.padding(top = 10.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF000000),
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE6E6E6),
                        focusedBorderColor = Color(0xFFE6E6E6)
                    )
                )
                FilledTonalButton(
                    { onSaveClicked(text) },
                    modifier = Modifier
                        .align(Alignment.End),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 6.dp
                    ),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(0xFF4664FF),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "Save", style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun AlarmFieldChangeDialogPreview() {
    SnoozelooBackground {
        AlarmFieldChangeDialog(
            onDismiss = {},
            onSaveClicked = { },
            fieldText = "Alarm",
        )
    }
}