package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun SudokuScreen(viewModel: SudokuViewModel = hiltViewModel()) {
    val state = viewModel.uiState.collectAsState()

    Column {
        // Selector de tamaño
        Row {
            Button(onClick = { viewModel.loadPuzzle(4, "easy") }) {
                Text("4x4")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.loadPuzzle(9, "easy") }) {
                Text("9x9")
            }
        }

        // Selector de dificultad
        Row {
            listOf("easy", "medium", "hard").forEach { difficulty ->
                Button(
                    onClick = {
                        val size = state.value.puzzle?.puzzle?.size ?: 9
                        viewModel.loadPuzzle(size, difficulty)
                    },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(difficulty.capitalize())
                }
            }
        }

        // Mostrar el tablero
        state.value.puzzle?.let { puzzle ->
            SudokuBoard(puzzle = puzzle.puzzle)
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