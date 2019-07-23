package com.example.kotlinproject.global.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.ColorInt
import com.example.kotlinproject.R
import com.example.kotlinproject.global.sharedPref.PreferenceMgr

class ThemeColors(context: Context, preferenceMgr: PreferenceMgr) {

    @ColorInt
    var color: Int = 0

    private// Checking if title text color will be black
    val isLightActionBar: Boolean
        get() {
            val rgb = (Color.red(color) + Color.green(color) + Color.blue(color)) / 3
            return rgb > 210
        }

    init {
//        val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val stringColor = preferenceMgr.getThemeColor()//sharedPreferences.getString(KEY, "004bff")
        color = Color.parseColor("#" + stringColor!!)

        if (isLightActionBar) context.setTheme(R.style.AppTheme)
        context.setTheme(context.getResources().getIdentifier("T_" + stringColor!!, "style", context.getPackageName()))
    }

    companion object {


        fun setNewThemeColor(activity: Activity, red: Int, green: Int, blue: Int, preferenceMgr: PreferenceMgr) {
            var red = red
            var green = green
            var blue = blue
            val colorStep = 15
            red = Math.round((red / colorStep).toFloat()) * colorStep
            green = Math.round((green / colorStep).toFloat()) * colorStep
            blue = Math.round((blue / colorStep).toFloat()) * colorStep

            val stringColor = Integer.toHexString(Color.rgb(red, green, blue)).substring(2)
            preferenceMgr.setThemeColor(stringColor)
//            val editor = activity.getSharedPreferences(NAME, Context.MODE_PRIVATE).edit()
//            editor.putString(KEY, stringColor)
//            editor.apply()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                activity.recreate()
            else {
                val i = activity.packageManager.getLaunchIntentForPackage(activity.packageName)
                i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                activity.startActivity(i)
            }
        }
    }
}