package com.dev2_002.tictactoe.domain

class Game {
    var currentPlayer: Player = Player.X
        private set

    var state: GameState = GameState.InProgress
        private set

    private val board = Board()

    fun getCell(row: Int, col: Int): Player? = board.getCell(row, col)

    fun makeMove(row: Int, col: Int): Boolean {
        if (row !in 0..2 || col !in 0..2) return false
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
        checkRows()
        checkColumns()
        checkDiagonals()
    }

    private fun checkRows() {
        for (row in 0..2) {
            if (isWinningLine(board.getCell(row, 0), board.getCell(row, 1), board.getCell(row, 2))) {
                state = GameState.Won(board.getCell(row, 0)!!)
                return
            }
        }
    }

    private fun checkColumns() {
        if (state != GameState.InProgress) return
        for (col in 0..2) {
            if (isWinningLine(board.getCell(0, col), board.getCell(1, col), board.getCell(2, col))) {
                state = GameState.Won(board.getCell(0, col)!!)
                return
            }
        }
    }

    private fun checkDiagonals() {
        if (state != GameState.InProgress) return
        if (isWinningLine(board.getCell(0, 0), board.getCell(1, 1), board.getCell(2, 2))) {
            state = GameState.Won(board.getCell(0, 0)!!)
            return
        }
        if (isWinningLine(board.getCell(0, 2), board.getCell(1, 1), board.getCell(2, 0))) {
            state = GameState.Won(board.getCell(0, 2)!!)
        }
    }

    private fun isWinningLine(a: Player?, b: Player?, c: Player?): Boolean {
        return a != null && a == b && b == c
    }

    private fun checkDraw() {
        if (state != GameState.InProgress) return
        if (board.isFull()) state = GameState.Draw
    }
}
