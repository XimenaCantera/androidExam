package com.example.kotlin.mysudoku.data.remote.dto

data class SudokuDto(
    val puzzle: List<List<Int?>>,  // <- Listas de Int nullable
    val solution: List<List<Int>>  // La solución nunca tiene nulls
)