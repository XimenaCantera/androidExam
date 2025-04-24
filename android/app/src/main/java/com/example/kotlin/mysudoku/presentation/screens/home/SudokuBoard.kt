package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SudokuBoard() {
    // Aquí iría la lógica para crear el tablero de Sudoku
    // Vamos a usar un ejemplo básico con 9x9 celdas

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(9) { rowIndex ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(9) { colIndex ->
                    TextField(
                        value = "", // Aquí iría el valor de la celda, actualizable desde ViewModel
                        onValueChange = { /* Lógica para actualizar el valor de la celda */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        singleLine = true,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSudokuBoard() {
    SudokuBoard()
}
