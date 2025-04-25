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
    private var initialPuzzleState: List<List<Int>> = emptyList()

    fun loadPuzzle(size: Int, difficulty: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val puzzle = generateSudokuUseCase(size, difficulty)
                initialPuzzleState = puzzle.puzzle.map { it.toList() }
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        puzzle = puzzle,
                        cellErrors = emptySet()
                    )
                }
            } catch (e: Exception) {
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
    fun checkSolution() {
        _uiState.update { currentState ->
            currentState.puzzle?.let { puzzle ->
                val errors = mutableSetOf<Pair<Int, Int>>()
                var allCorrect = true
                var allFilled = true

                puzzle.puzzle.forEachIndexed { row, rowCells ->
                    rowCells.forEachIndexed { col, value ->
                        if (value == 0) {
                            allFilled = false
                        } else if (value != puzzle.solution[row][col]) {
                            errors.add(Pair(row, col))
                            allCorrect = false
                        }
                    }
                }

                currentState.copy(
                    cellErrors = errors,
                    isCorrect = if (allFilled) allCorrect else null,
                    showSolutionCheck = true
                )
            } ?: currentState
        }
    }

    fun resetVerification() {
        _uiState.update {
            it.copy(
                cellErrors = emptySet(),
                isCorrect = null,
                showSolutionCheck = false
            )
        }
    }

    // Guarda el puzzle actual
    fun resetCurrentPuzzle() {
        _uiState.update { currentState ->
            currentState.puzzle?.let { puzzle ->
                currentState.copy(
                    puzzle = puzzle.copy(
                        puzzle = initialPuzzleState.map { it.toMutableList() }
                    ),
                    cellErrors = emptySet()
                )
            } ?: currentState
        }
    }

    // Nuevo puzzle
    fun loadNewPuzzle(size: Int, difficulty: String) {
        saveCurrentPuzzle()
        loadPuzzle(size, difficulty)
    }

    // Guardar el puzzle actual
    fun saveCurrentPuzzle() {
        _uiState.update { currentState ->
            currentState.puzzle?.let { puzzle ->
                val key = "${puzzle.size}-${puzzle.difficulty}"
                currentState.copy(
                    savedPuzzles = currentState.savedPuzzles + (key to puzzle)
                )
            } ?: currentState
        }
    }

}