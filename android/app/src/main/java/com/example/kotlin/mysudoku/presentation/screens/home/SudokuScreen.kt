package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SudokuScreen(viewModel: SudokuViewModel = hiltViewModel()) {
    val state = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.loadPuzzle(3, 3, "easy") // Ejemplo 9x9, dificultad fácil
    }

    when {
        state.isLoading -> CircularProgressIndicator()
        state.error != null -> Text(text = state.error ?: "Error desconocido")
        state.puzzle != null -> {
            Column {
                state.puzzle.forEach { row ->
                    Row {
                        row.forEach { cell ->
                            Text(text = if (cell == 0) "" else cell.toString(), modifier = Modifier.padding(4.dp))
                        }
                    }
                }
            }
        }
    }
}
