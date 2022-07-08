package com.hly.improve.widget

import android.app.IntentService
import android.content.Intent

class MyService : IntentService("MyService") {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(p0: Intent?) {
        for (i in 0 until 60) {
            Thread.sleep(1000)
            val intent = Intent(AppWidget.ACTION_UPDATE)
            intent.setPackage(this.packageName)
            intent.putExtra("data", "$i s")
            sendBroadcast(intent)
        }
    }
}