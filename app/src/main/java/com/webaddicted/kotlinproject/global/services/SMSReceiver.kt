package com.webaddicted.kotlinproject.global.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status

class SMSReceiver : BroadcastReceiver() {
    val TAG = SMSReceiver::class.java.simpleName
    /**
     * @param context
     * @param intent
     */
    override fun onReceive(context: Context, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {
            val extras = intent.extras
            val status = extras!!.get(SmsRetriever.EXTRA_STATUS) as Status?
            when (status!!.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    //This is the full message
                    val message = extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String?
                    Log.d(TAG, "SUCCESS -> "+message)
                    /*<#> Your ExampleApp code is: 123ABC78
                    FA+9qCX9VSu*/
                    //Extract the OTP code and send to the listener
                }
                CommonStatusCodes.TIMEOUT ->Log.d(TAG, "TIME out")
                    // Waiting for SMS timed out (5 minutes)
                CommonStatusCodes.API_NOT_CONNECTED ->  Log.d(TAG, "API NOT CONNECTED")
                CommonStatusCodes.NETWORK_ERROR ->Log.d(TAG, "NETWORK ERROR")
                CommonStatusCodes.ERROR ->Log.d(TAG, "SOME THING WENT WRONG")
            }
        }
    }
}