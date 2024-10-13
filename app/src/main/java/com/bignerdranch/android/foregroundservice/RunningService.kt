package com.bignerdranch.android.foregroundservice

import android.app.Notification
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.Build
import android.os.IBinder


class RunningService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notification =
                Notification.Builder(this, "running_channel")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Running service started!")
                    .setContentText("starting now...")
                    .build()
            startForeground(1, notification)
        }
    }

    enum class Actions {
        START, STOP
    }
}