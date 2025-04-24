package com.example.kotlin.mysudoku.data.remote.api

import com.example.kotlin.mysudoku.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SudokuApi {
    @GET("sudokugenerate")
    suspend fun generateSudoku(
        @Query("width") width: Int,
        @Query("height") height: Int,
        @Query("difficulty") difficulty: String
    ): SudokuDto
}
