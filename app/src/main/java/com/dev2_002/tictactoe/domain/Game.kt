package com.dev2_002.tictactoe.domain

class Game {
    var currentPlayer: Player = Player.X
        private set

    private val board = Array(3) { arrayOfNulls<Player>(3) }

    fun makeMove(row: Int, col: Int) {
        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }
}
