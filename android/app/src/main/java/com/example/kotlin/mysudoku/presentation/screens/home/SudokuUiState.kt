package com.example.kotlin.mysudoku.presentation.screens.home

data class SudokuUiState(
    val isLoading: Boolean = false,
    val puzzle: List<List<Int>>? = null,
    val error: String? = null
)
