package com.example.kotlin.mysudoku.domain.usecase

import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle
import com.example.kotlin.mysudoku.domain.repository.SudokuRepository

class GenerateSudokuUseCase(private val repository: SudokuRepository) {
    suspend operator fun invoke(width: Int, height: Int, difficulty: String): SudokuPuzzle {
        return repository.generateSudoku(width, height, difficulty)
    }
}
