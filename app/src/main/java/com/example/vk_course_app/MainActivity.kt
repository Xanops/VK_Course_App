package com.example.vk_course_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.vk_course_app.ui.theme.VK_Course_AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VK_Course_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IntentButtons(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IntentButtons(modifier: Modifier = Modifier) {
    val focusRequester = remember { FocusRequester() }
    val tfState = rememberTextFieldState()

    val context = LocalContext.current
    Column (
        modifier = modifier.fillMaxSize().
            // 2. Привязываем FocusRequester к Column
            focusRequester(focusRequester).
            // 3. Делаем Column фокусируемой, но без эффекта клика при нажатии на пустую область
            focusable(interactionSource = remember { MutableInteractionSource() }),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            state = tfState,
            label = { Text("TextField") })
        Button(
            onClick = { Utils.OpenExplicit(context, tfState.text.toString()) }
        ) {
            Text("Открыть вторую Activity")
        }
        Button(
            onClick = { Utils.OpenImplicit(context, tfState.text.toString()) }
        ) {
            Text("Позвонить другу")
        }
        Button(
            onClick = { Utils.OpenSystemIntent(context, tfState.text.toString()) }
        ) {
            Text("Поделиться текстом")
        }
    }
}