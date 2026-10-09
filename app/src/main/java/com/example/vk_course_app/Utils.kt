package com.example.vk_course_app

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

object Utils {
    fun OpenImpilicit(context: Context) {
        val ImplicitIntent = Intent(Intent.ACTION_VIEW).apply {
            data = "lol".toUri()
        }
        context.startActivity(ImplicitIntent)
    }

    fun OpenExplicit(context: Context) {
        val explicitIntent = Intent(context, MainActivity::class.java)
        explicitIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.startActivity(explicitIntent)
    }
}