package com.example.kotlin.mysudoku.domain.model

fun SudokuPuzzle.to4x4(): SudokuPuzzle {
    require(this.size == 9) { "Solo se puede convertir de 9x9 a 4x4" }

    return SudokuPuzzle(
        puzzle = this.puzzle.take(4).map { it.take(4) },
        solution = this.solution.take(4).map { it.take(4) },
        size = 4,
        difficulty = "medium"
    )
}