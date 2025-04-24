package com.example.kotlin.mysudoku.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.example.kotlin.mysudoku.presentation.screens.home.SudokuScreen
import com.example.kotlin.mysudoku.ui.theme.MySudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MySudokuTheme {
                Scaffold {
                    SudokuScreen() // Llamamos a la pantalla del Sudoku
                }
            }
        }
    }
}
