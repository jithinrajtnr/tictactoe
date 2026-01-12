package com.dev2_002.tictactoe.domain

class Game {
    var currentPlayer: Player = Player.X
        private set

    private val board = Array(3) { arrayOfNulls<Player>(3) }

    fun makeMove(row: Int, col: Int): Boolean {
        if (board[row][col] != null) return false
        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        return true
    }
}
