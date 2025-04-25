package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mysudoku.domain.usecase.GenerateSudokuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SudokuViewModel @Inject constructor(
    private val generateSudokuUseCase: GenerateSudokuUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SudokuUiState())
    val uiState: StateFlow<SudokuUiState> = _uiState.asStateFlow()

    fun loadPuzzle(size: Int, difficulty: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val puzzle = generateSudokuUseCase(size, difficulty)
                _uiState.update { it.copy(
                    isLoading = false,
                    puzzle = puzzle
                ) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    isLoading = false,
                    error = e.message
                ) }
            }
        }
    }

    fun onCellValueChanged(row: Int, col: Int, value: Int) {
        _uiState.update { currentState ->
            currentState.puzzle?.let { puzzle ->
                if (puzzle.editableCells.contains(Pair(row, col))) {
                    val newPuzzle = puzzle.puzzle.toMutableList().map { it.toMutableList() }
                    newPuzzle[row][col] = value
                    currentState.copy(
                        puzzle = puzzle.copy(
                            puzzle = newPuzzle.map { it.toList() }
                        )
                    )
                } else {
                    currentState
                }
            } ?: currentState
        }
    }
}