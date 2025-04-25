package com.example.kotlin.mysudoku.data.mapper

import com.example.kotlin.mysudoku.data.remote.dto.SudokuDto
import com.example.kotlin.mysudoku.domain.model.SudokuPuzzle

fun SudokuDto.toDomain(): SudokuPuzzle {
    return SudokuPuzzle(
        puzzle = puzzle.map { row ->
            row.map { cell -> cell ?: 0 }  // Convierte null → 0
        },
        solution = solution
    )
}