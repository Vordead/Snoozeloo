package com.vordead.snoozeloo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

const val DEEPLINK_DOMAIN = "vordead.com"

@HiltAndroidApp
class SnoozelooApplication : Application()