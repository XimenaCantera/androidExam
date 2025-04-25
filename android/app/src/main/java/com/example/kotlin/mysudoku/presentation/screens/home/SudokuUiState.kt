package com.example.kotlin.mysudoku.presentation.screens.home

import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle

data class SudokuUiState(
    val isLoading: Boolean = false,
    val puzzle: SudokuPuzzle? = null,
    val savedPuzzles: Map<String, SudokuPuzzle> = emptyMap(),
    val error: String? = null,
    val cellErrors: Set<Pair<Int, Int>> = emptySet(),
    val isCorrect: Boolean? = null,
    val showSolutionCheck: Boolean = false
)