package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin.mysudoku.ui.theme.MySudokuTheme

@Composable
fun SudokuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sudoku Game", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el tablero de Sudoku
        SudokuBoard()

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para verificar la solución
        Button(onClick = { /* Acción para verificar la solución */ }) {
            Text("Verificar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSudokuScreen() {
    MySudokuTheme {
        SudokuScreen()
    }
}
