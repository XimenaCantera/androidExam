package com.example.kotlin.mysudoku.data.remote.dto

data class SudokuDto(
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>
)
