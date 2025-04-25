package com.example.kotlin.mysudoku.domain.usecase

import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle
import com.example.kotlin.mysudoku.domain.repository.SudokuRepository

class GenerateSudokuUseCase(private val repository: SudokuRepository) {
    suspend operator fun invoke(difficulty: String): SudokuPuzzle {  // Eliminamos 'size'
        return repository.generateSudoku(difficulty)  // Llama al método actualizado
    }
}