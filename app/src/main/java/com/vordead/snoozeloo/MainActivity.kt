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
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            SnoozelooTheme {
                SnoozelooApp()
            }
        }
    }
}