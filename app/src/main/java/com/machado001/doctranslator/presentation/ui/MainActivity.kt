package com.machado001.doctranslator.presentation.ui

import FileResultObserver
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.machado001.doctranslator.di.Application
import com.machado001.doctranslator.presentation.theme.DocTranslatorTheme


class MainActivity : ComponentActivity() {

    private lateinit var observer: FileResultObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val container = (application as Application).container

        observer = FileResultObserver(
            translator = container.translator,
            registry = activityResultRegistry,
            context = this
        ).also { lifecycle.addObserver(it) }

        enableEdgeToEdge()
        setContent {
            DocTranslatorTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                ) { innerPadding ->
                    Screen(modifier = Modifier.padding(innerPadding)) { observer.openFile() }
                }
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier, onOpenFile: () -> Unit) {
    Button(onClick = onOpenFile) {
        Text(text = "Pick PDF")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DocTranslatorTheme {
        Screen {

        }
    }
}