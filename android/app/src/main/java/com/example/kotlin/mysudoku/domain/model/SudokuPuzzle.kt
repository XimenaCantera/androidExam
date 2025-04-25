package com.example.kotlin.mysudoku.domain.model

data class SudokuPuzzle(
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>,
    val editableCells: List<Pair<Int, Int>> = emptyList() // Celdas modificables (ej: donde puzzle[i][j] == 0)
) {
    fun isComplete(): Boolean {
        return puzzle.flatten().none { it == 0 }
    }

    fun isCorrect(): Boolean {
        return puzzle == solution
    }
}