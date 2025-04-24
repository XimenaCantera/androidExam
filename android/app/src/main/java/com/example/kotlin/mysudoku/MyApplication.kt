package com.example.kotlin.mysudoku

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp  // Hilt se encarga de la inyección de dependencias
class MyApplication : Application() {
    // Puedes agregar lógica de inicialización si es necesario
}
