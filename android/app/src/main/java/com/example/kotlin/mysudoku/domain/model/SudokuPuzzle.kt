package com.example.kotlin.mysudoku.domain.model

data class SudokuPuzzle(
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>
)
