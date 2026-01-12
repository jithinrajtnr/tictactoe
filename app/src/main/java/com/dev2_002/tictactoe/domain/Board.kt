package com.dev2_002.tictactoe.domain

class Board {
    private val cells = Array(3) { arrayOfNulls<Player>(3) }

    fun getCell(row: Int, col: Int): Player? = cells[row][col]

    fun setCell(row: Int, col: Int, player: Player) {
        cells[row][col] = player
    }

    fun isFull(): Boolean = cells.all { row -> row.all { it != null } }

    fun clear() {
        for (i in 0..2) {
            for (j in 0..2) {
                cells[i][j] = null
            }
        }
    }
}
