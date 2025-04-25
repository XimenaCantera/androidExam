package com.example.kotlin.mysudoku.presentation.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SudokuBoard(puzzle: List<List<Int>>) {
    val cellSize = if (puzzle.size == 9) 40.dp else 60.dp

    Column(
        modifier = Modifier
            .border(2.dp, Color.Black)
            .padding(4.dp)
    ) {
        puzzle.forEach { row ->
            Row {
                row.forEach { cell ->
                    SudokuCell(
                        value = cell,
                        size = cellSize
                    )
                }
            }
        }
    }
}

@Composable
fun SudokuCell(value: Int, size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .border(1.dp, Color.Gray)
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(if (value == 0) "" else "$value")
    }
}