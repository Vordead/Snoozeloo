package com.vordead.snoozeloo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vordead.snoozeloo.alarm.presentation.AlarmScheduler
import com.vordead.snoozeloo.core.presentation.SnoozelooApp
import com.vordead.snoozeloo.ui.theme.SnoozelooTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.POST_NOTIFICATIONS,
                    android.Manifest.permission.USE_FULL_SCREEN_INTENT,
                    android.Manifest.permission.USE_EXACT_ALARM,
                    android.Manifest.permission.SCHEDULE_EXACT_ALARM
                ),
                1
            )
        }
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2024)
            set(Calendar.MONTH, Calendar.NOVEMBER)
            set(Calendar.DAY_OF_MONTH, 27)
            set(Calendar.HOUR_OF_DAY, 19) // 7 PM
            set(Calendar.MINUTE, 55) // 55 minutes
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val timeInMillis = calendar.timeInMillis
        println("Time in milliseconds: $timeInMillis")
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                SnoozelooApp()
            }
        }
    }
}