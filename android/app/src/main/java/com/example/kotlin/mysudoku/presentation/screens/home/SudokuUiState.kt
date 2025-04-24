package com.example.kotlin.mysudoku.presentation.screens.home

data class SudokuUiState(
    val isLoading: Boolean = false,
    val puzzle: List<List<Int>>? = null,
    val userInput: List<MutableList<String>>? = null,  // Editable
    val solution: List<List<Int>>? = null,
    val error: String? = null,
    val isCorrect: Boolean? = null                     // Resultado de verificación
)

