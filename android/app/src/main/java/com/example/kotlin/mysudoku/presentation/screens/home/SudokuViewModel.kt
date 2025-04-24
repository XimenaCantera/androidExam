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
class SudokuViewModel @Inject constructor(  // ← Esta línea debe tener @Inject
    private val generateSudoku: GenerateSudokuUseCase
) : ViewModel() {

    var uiState by mutableStateOf(SudokuUiState())
        private set

    fun loadPuzzle(width: Int, height: Int, difficulty: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            try {
                val puzzle = generateSudoku(width, height, difficulty)
                uiState = uiState.copy(puzzle = puzzle.puzzle, isLoading = false)
            } catch (e: Exception) {
                uiState = uiState.copy(error = "Error al cargar puzzle", isLoading = false)
            }
        }
    }
}
