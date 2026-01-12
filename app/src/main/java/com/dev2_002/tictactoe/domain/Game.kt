package com.dev2_002.tictactoe.domain

class Game {
    var currentPlayer: Player = Player.X
        private set

    var state: GameState = GameState.InProgress
        private set

    private val board = Board()

    fun getCell(row: Int, col: Int): Player? = board.getCell(row, col)

    fun makeMove(row: Int, col: Int): Boolean {
        if (state != GameState.InProgress) return false
        if (board.getCell(row, col) != null) return false
        board.setCell(row, col, currentPlayer)
        checkWinner()
        checkDraw()
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        return true
    }

    fun reset() {
        board.clear()
        currentPlayer = Player.X
        state = GameState.InProgress
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (board.getCell(i, 0) != null && 
                board.getCell(i, 0) == board.getCell(i, 1) && 
                board.getCell(i, 1) == board.getCell(i, 2)) {
                state = GameState.Won(board.getCell(i, 0)!!)
                return
            }
            if (board.getCell(0, i) != null && 
                board.getCell(0, i) == board.getCell(1, i) && 
                board.getCell(1, i) == board.getCell(2, i)) {
                state = GameState.Won(board.getCell(0, i)!!)
                return
            }
        }
        if (board.getCell(0, 0) != null && 
            board.getCell(0, 0) == board.getCell(1, 1) && 
            board.getCell(1, 1) == board.getCell(2, 2)) {
            state = GameState.Won(board.getCell(0, 0)!!)
            return
        }
        if (board.getCell(0, 2) != null && 
            board.getCell(0, 2) == board.getCell(1, 1) && 
            board.getCell(1, 1) == board.getCell(2, 0)) {
            state = GameState.Won(board.getCell(0, 2)!!)
            return
        }
    }

    private fun checkDraw() {
        if (state != GameState.InProgress) return
        if (board.isFull()) state = GameState.Draw
    }
}
