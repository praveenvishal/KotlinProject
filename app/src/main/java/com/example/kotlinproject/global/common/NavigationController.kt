package com.example.kotlinproject.global.common

import android.content.Context
import android.content.Intent
import com.example.kotlinproject.view.home.HomeActivity
import com.example.kotlinproject.view.language.LanguageActivity
import com.example.kotlinproject.view.login.LoginActivity
import com.example.kotlinproject.view.map.MapActivity
import com.example.kotlinproject.view.onBoarding.OnBoardActivity

class NavigationController {
    /**
     * navigate on LanguageActivity
     */
    fun navigateToLanguage(context: Context) {
        context.startActivity(Intent(context, LanguageActivity::class.java))
    }
    /**
     * navigate on OnBoardActivity
     */
    fun navigateToOnBoard(context: Context) {
        context.startActivity(Intent(context, OnBoardActivity::class.java))
    }
    /**
     * navigate on LoginActivity
     */
    fun navigateToLogin(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
    /**
     * navigate on HomeActivity
     */
    fun navigateToHome(context: Context) {
        context.startActivity(Intent(context, HomeActivity::class.java))
    }
    /**
     * navigate on MapActivity
     */
    fun navigateToMap(context: Context) {
        context.startActivity(Intent(context, MapActivity::class.java))
    }
}