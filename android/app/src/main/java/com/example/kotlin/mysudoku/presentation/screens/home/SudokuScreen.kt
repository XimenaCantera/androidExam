package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SudokuScreen(
    viewModel: SudokuViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    // Cargar puzzle al iniciar (opcional)
    LaunchedEffect(Unit) {
        viewModel.loadPuzzle("easy") // Dificultad por defecto
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Botones de dificultad
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Button(onClick = { viewModel.loadPuzzle("easy") }) {
                Text("Fácil")
            }
            Button(onClick = { viewModel.loadPuzzle("medium") }) {
                Text("Medio")
            }
            Button(onClick = { viewModel.loadPuzzle("hard") }) {
                Text("Difícil")
            }
        }

        when {
            state.value.isLoading -> {
                CircularProgressIndicator()
            }

            state.value.error != null -> {
                Text(
                    text = "Error: ${state.value.error}",
                    color = Color.Red,
                    fontSize = 18.sp
                )
            }

            state.value.puzzle != null -> {
                // Mostrar el tablero de Sudoku
                SudokuBoard(
                    puzzle = state.value.puzzle!!.puzzle,
                    modifier = Modifier.padding(8.dp)
                )

                // Mensaje si está completado
                if (state.value.isCorrect == true) {
                    Text(
                        text = "¡Correcto!",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SudokuBoard(
    puzzle: List<List<Int>>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        puzzle.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                row.forEach { cell ->
                    SudokuCell(
                        value = cell,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun SudokuCell(
    value: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = if (value == 0) " " else value.toString(),
        modifier = modifier
            .padding(4.dp),
        fontSize = 20.sp,
        color = if (value == 0) Color.Gray else Color.Black
    )
}