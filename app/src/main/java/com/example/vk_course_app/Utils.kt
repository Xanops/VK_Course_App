package com.example.vk_course_app

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

object Utils {
    fun OpenExplicit(context: Context, text_from_TextField: String) {
        val explicitIntent = Intent(context, Activity2::class.java).putExtra("inf", text_from_TextField)
        context.startActivity(explicitIntent)
    }

    fun OpenImplicit(context: Context, text_from_TextField: String) {
        val implicitIntent = Intent(Intent.ACTION_DIAL).apply { data = "tel:${text_from_TextField.trim()}".toUri() }
        implicitIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        try {
            context.startActivity(implicitIntent)
        } catch (e: ActivityNotFoundException) {
            // Обрабатываем случай, если на устройстве нет диалера
            Toast.makeText(context, "Приложение для звонков не найдено", Toast.LENGTH_SHORT).show()
        }
    }

    fun OpenSystemIntent(context: Context, text_from_TextField: String) {
        val systemIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text_from_TextField)
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        try {
            val chooserIntent = Intent.createChooser(systemIntent, "Поделиться с помощью")
            context.startActivity(chooserIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Нет приложений для отправки текста", Toast.LENGTH_SHORT).show()
        }
    }
}