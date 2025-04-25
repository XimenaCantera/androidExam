package com.example.kotlin.mysudoku.data.repository

import com.example.kotlin.mysudoku.data.mapper.toDomain
import com.example.kotlin.mysudoku.data.remote.api.SudokuApi
import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle
import com.example.kotlin.mysudoku.domain.repository.SudokuRepository

// data/repository/SudokuRepositoryIm.kt
class SudokuRepositoryIm(
    private val api: SudokuApi
) : SudokuRepository {
    override suspend fun generateSudoku(difficulty: String): SudokuPuzzle {  // Sin size
        println("🛠️ Llamando a API con dificultad: $difficulty")
        try {
            val response = api.generateSudoku(difficulty)  // Solo pasamos difficulty
            println("✅ Respuesta API: Tablero ${response.puzzle.size}x${response.puzzle[0].size}")
            return response.toDomain()
        } catch (e: Exception) {
            println("❌ Error en API: ${e.javaClass.simpleName} - ${e.message}")
            throw e
        }
    }
}