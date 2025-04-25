package com.example.kotlin.mysudoku.data.remote.api

import com.example.kotlin.mysudoku.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SudokuApi {
    @GET("sudokugenerate")  // Endpoint confirmado
    suspend fun generateSudoku(
        @Query("difficulty") difficulty: String  // Solo este parámetro
    ): SudokuDto
}