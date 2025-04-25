package com.example.kotlin.mysudoku.data.repository

import com.example.kotlin.mysudoku.data.mapper.toDomain
import com.example.kotlin.mysudoku.data.remote.api.SudokuApi
import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle
import com.example.kotlin.mysudoku.domain.repository.SudokuRepository

class SudokuRepositoryIm(
    private val api: SudokuApi
) : SudokuRepository {
    override suspend fun generateSudoku(difficulty: String): SudokuPuzzle {
        val response = api.generateSudoku(difficulty)
        return response.toDomain()
    }
}