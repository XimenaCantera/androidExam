package com.example.kotlin.mysudoku.domain.model

data class SudokuPuzzle(
    val puzzle: List<List<Int>>,
    val solution: List<List<Int>>,
    val size: Int
) {
    val editableCells: Set<Pair<Int, Int>> by lazy {
        puzzle.mapIndexedNotNull { i, row ->
            row.mapIndexedNotNull { j, cell ->
                if (cell == 0) Pair(i, j) else null
            }
        }.flatten().toSet()
    }
}
