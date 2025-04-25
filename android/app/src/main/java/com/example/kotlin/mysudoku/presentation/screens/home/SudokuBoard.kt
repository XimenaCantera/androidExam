package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SudokuBoard(puzzle: List<List<Int>>) {
    Column {
        puzzle.forEach { row ->
            Row {
                row.forEach { cell ->
                    Text(text = if (cell == 0) "" else "$cell")
                }
            }
        }
    }
}