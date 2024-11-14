package com.vordead.snoozeloo.alarm.presentation.alarm_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vordead.snoozeloo.R
import com.vordead.snoozeloo.ui.theme.SnoozelooTheme

@Composable
fun AlarmDetailAppBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_discard),
            contentDescription = "Back",
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .clickable { onBackClick() }
        )

        FilledTonalButton(
            onSaveClick,
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color(0xFF4664FF)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
        ) {
            Text(
                "Save",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600)
                )
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AlarmDetailAppBarPreview() {
    SnoozelooTheme {
        AlarmDetailAppBar(
            onBackClick = {},
            onSaveClick = {},
            modifier = Modifier.systemBarsPadding()
        )
    }
}