package com.webaddicted.kotlinproject.global.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.annotation.Nullable

class NormalService : Service() {
    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)

    }
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        //sticky use when phone is turn off...when user on phone then it try to start service
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show()
    }
}