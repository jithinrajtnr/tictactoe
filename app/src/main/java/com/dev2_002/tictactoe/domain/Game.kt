package com.dev2_002.tictactoe.domain

class Game {
    var currentPlayer: Player = Player.X
        private set

    var state: GameState = GameState.InProgress
        private set

    private val board = Array(3) { arrayOfNulls<Player>(3) }

    fun getCell(row: Int, col: Int): Player? = board[row][col]

    fun makeMove(row: Int, col: Int): Boolean {
        if (state != GameState.InProgress) return false
        if (board[row][col] != null) return false
        board[row][col] = currentPlayer
        checkWinner()
        checkDraw()
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        return true
    }

    fun reset() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = null
            }
        }
        currentPlayer = Player.X
        state = GameState.InProgress
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                state = GameState.Won(board[i][0]!!)
                return
            }
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                state = GameState.Won(board[0][i]!!)
                return
            }
        }
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            state = GameState.Won(board[0][0]!!)
            return
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            state = GameState.Won(board[0][2]!!)
            return
        }
    }

    private fun checkDraw() {
        if (state != GameState.InProgress) return
        val isFull = board.all { row -> row.all { it != null } }
        if (isFull) state = GameState.Draw
    }
}
