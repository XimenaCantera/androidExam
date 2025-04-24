package com.example.kotlin.mysudoku.domain.repository

import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle

interface SudokuRepository {
    suspend fun generateSudoku(width: Int, height: Int, difficulty: String): SudokuPuzzle
}
