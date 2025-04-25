package com.example.kotlin.mysudoku.domain.usecase

import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle
import com.example.kotlin.mysudoku.domain.model.to4x4
import com.example.kotlin.mysudoku.domain.repository.SudokuRepository
import javax.inject.Inject

class GenerateSudokuUseCase @Inject constructor(
    private val repository: SudokuRepository
) {
    suspend operator fun invoke(size: Int, difficulty: String): SudokuPuzzle {
        val puzzle = repository.generateSudoku(difficulty)
        return if (size == 4) puzzle.to4x4() else puzzle
    }
}