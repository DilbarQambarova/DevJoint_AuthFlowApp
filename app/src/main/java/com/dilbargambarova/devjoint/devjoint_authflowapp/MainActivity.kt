package com.dilbargambarova.devjoint.devjoint_authflowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.dilbargambarova.devjoint.devjoint_authflowapp.presentation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Tətbiqin əsas dizayn mövzusu
            MaterialTheme {
                // Ekranın arxa fonu və ölçüsü
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Əvvəlki addımda yaratdığımız Naviqasiya funksiyasını çağırırıq
                    AppNavigation()
                }
            }
        }
    }
}