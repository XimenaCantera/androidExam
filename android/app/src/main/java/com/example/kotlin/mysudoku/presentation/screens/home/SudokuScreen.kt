package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle

@Composable
fun SudokuScreen(viewModel: SudokuViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    var selectedSize by remember { mutableStateOf(9) }
    var selectedDifficulty by remember { mutableStateOf("easy") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Selector de TAMAÑO (4x4 o 9x9)
        Text("Selecciona tamaño:", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(4, 9).forEach { size ->
                FilterChip(
                    selected = (size == selectedSize),
                    onClick = { selectedSize = size },
                    label = { Text("${size}x$size") }
                )
            }
        }

        // Selector de DIFICULTAD
        Text("Selecciona dificultad:", style = MaterialTheme.typography.titleMedium)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("easy", "medium", "hard").forEach { difficulty ->
                FilterChip(
                    selected = (difficulty == selectedDifficulty),
                    onClick = { selectedDifficulty = difficulty },
                    label = { Text(difficulty.replaceFirstChar { it.uppercase() }) }
                )
            }
        }

        // Botón para generar
        Button(
            onClick = { viewModel.loadPuzzle(selectedSize, selectedDifficulty) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Generar Sudoku")
        }

        Button(
            onClick = { viewModel.checkSolution() },
            enabled = state.puzzle != null
        ) {
            Text("Verificar Solución")
        }

        // Mensaje de resultado
        if (state.showSolutionCheck) {
            Text(
                text = when (state.isCorrect) {
                    true -> "¡Correcto!"
                    false -> "Incorrecto"
                    else -> "Verifica tu solución"
                },
                color = when (state.isCorrect) {
                    true -> Color.Green
                    false -> Color.Red
                    else -> Color.Black
                }
            )
        }

        // Tablero
        when {
            state.isLoading -> CircularProgressIndicator()
            state.error != null -> Text("Error: ${state.error}", color = Color.Red)
            state.puzzle != null -> {
                SudokuBoard(
                    puzzle = state.puzzle!!,
                    cellErrors = state.cellErrors,  // Añade este parámetro
                    onCellValueChanged = { row, col, value ->
                        viewModel.onCellValueChanged(row, col, value)
                    }
                )
            }
        }
    }
}

@Composable
fun SudokuBoard(
    puzzle: SudokuPuzzle,
    cellErrors: Set<Pair<Int, Int>>,
    onCellValueChanged: (Int, Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        puzzle.puzzle.forEachIndexed { rowIndex, row ->
            Row {
                row.forEachIndexed { colIndex, cell ->
                    SudokuCell(
                        value = cell,
                        isEditable = puzzle.editableCells.contains(Pair(rowIndex, colIndex)),
                        isError = cellErrors.contains(Pair(rowIndex, colIndex)),
                        onValueChange = { newValue ->
                            onCellValueChanged(rowIndex, colIndex, newValue)
                        },
                        modifier = Modifier
                            .weight(1f)
                            .padding(1.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SudokuCell(
    value: Int,
    isEditable: Boolean,
    isError: Boolean,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditing by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(
                when {
                    isError -> Color(0xFFFFCDD2).copy(alpha = 0.7f)  // Rojo claro semitransparente
                    !isEditable -> Color.LightGray.copy(alpha = 0.3f)
                    else -> Color.Transparent
                }
            )
            .border(1.dp, Color.Gray)
            .clickable(enabled = isEditable) { isEditing = true },
        contentAlignment = Alignment.Center
    ) {
        if (isEditing) {
            TextField(
                value = if (value == 0) "" else value.toString(),
                onValueChange = { newValue ->
                    if (newValue.isEmpty()) {
                        onValueChange(0)
                        isEditing = false
                    } else if (newValue.toIntOrNull() in 1..9) {
                        onValueChange(newValue.toInt())
                        isEditing = false
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.width(30.dp)
            )
        } else {
            Text(
                text = if (value == 0) "" else "$value",
                color = when {
                    !isEditable -> Color.Black
                    isError -> Color.Red
                    else -> Color.Blue
                }
            )
        }
    }
}