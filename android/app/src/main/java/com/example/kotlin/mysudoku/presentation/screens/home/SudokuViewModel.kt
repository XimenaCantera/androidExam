package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mysudoku.domain.usecase.GenerateSudokuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val generateSudoku: GenerateSudokuUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SudokuUiState())
        private set

    // Función para cargar un nuevo puzzle
    fun loadPuzzle(width: Int, height: Int, difficulty: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null, isCorrect = null)
            try {
                val puzzle = generateSudoku(width, height, difficulty)
                // Inicializar las celdas de usuario, vacías donde es 0 en el puzzle
                val userInput = puzzle.puzzle.map { row ->
                    row.map { if (it == 0) "" else it.toString() }.toMutableList()
                }
                uiState = uiState.copy(
                    puzzle = puzzle.puzzle,
                    userInput = userInput,
                    solution = puzzle.solution,
                    isLoading = false
                )
            } catch (e: Exception) {
                uiState = uiState.copy(error = "Error al cargar puzzle", isLoading = false)
            }
        }
    }

    // Función para actualizar el valor de una celda en el tablero
    fun updateCell(row: Int, col: Int, value: String) {
        uiState.userInput?.let { input ->
            input[row][col] = value
            uiState = uiState.copy(userInput = input)
        }
    }

    // Función para verificar la solución del puzzle
    fun checkSolution() {
        val correct = uiState.solution
        val input = uiState.userInput

        if (correct != null && input != null) {
            val isCorrect = correct.indices.all { row ->
                correct[row].indices.all { col ->
                    input[row][col].toIntOrNull() == correct[row][col]
                }
            }
            uiState = uiState.copy(isCorrect = isCorrect)
        }
    }
}