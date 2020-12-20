package ru.vladikadiroff.mediawikiprojects.utils.extensions

import android.os.Handler
import android.os.Looper
import android.util.Log


fun withPostDelay(action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(action, 2000)
}

fun Any.showLog(message: String) {
    Log.e(this.toString(), message)
}