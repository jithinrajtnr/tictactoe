package com.dev2_002.tictactoe.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for TicTacToe game logic.
 */
class GameTest {

    // Rule 1: X always goes first

    @Test
    fun `new game should start with player X`() {
        val game = Game()
        assertEquals(Player.X, game.currentPlayer)
    }

    @Test
    fun `first move should be made by player X`() {
        val game = Game()
        assertEquals(Player.X, game.currentPlayer)
    }

    // Rule 2: Players alternate

    @Test
    fun `after X plays it should be O turn`() {
        val game = Game()
        game.makeMove(0, 0)
        assertEquals(Player.O, game.currentPlayer)
    }

    @Test
    fun `after O plays it should be X turn`() {
        val game = Game()
        game.makeMove(0, 0)
        game.makeMove(0, 1)
        assertEquals(Player.X, game.currentPlayer)
    }

    @Test
    fun `players alternate correctly for multiple moves`() {
        val game = Game()
        assertEquals(Player.X, game.currentPlayer)
        game.makeMove(0, 0)
        assertEquals(Player.O, game.currentPlayer)
        game.makeMove(0, 1)
        assertEquals(Player.X, game.currentPlayer)
        game.makeMove(0, 2)
        assertEquals(Player.O, game.currentPlayer)
    }

    @Test
    fun `move on occupied cell should be rejected`() {
        val game = Game()
        game.makeMove(0, 0)
        val result = game.makeMove(0, 0)
        assertEquals(false, result)
    }

    @Test
    fun `rejected move should not change current player`() {
        val game = Game()
        game.makeMove(0, 0)
        val playerBefore = game.currentPlayer
        game.makeMove(0, 0)
        assertEquals(playerBefore, game.currentPlayer)
    }

    @Test
    fun `three X in top row should win`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(1, 0) // O
        game.makeMove(0, 1) // X
        game.makeMove(1, 1) // O
        game.makeMove(0, 2) // X wins
        assertEquals(GameState.Won(Player.X), game.state)
    }

    @Test
    fun `three O in middle row should win`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(1, 0) // O
        game.makeMove(0, 1) // X
        game.makeMove(1, 1) // O
        game.makeMove(2, 2) // X
        game.makeMove(1, 2) // O wins row 1
        assertEquals(GameState.Won(Player.O), game.state)
    }

    @Test
    fun `three X in first column should win`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(0, 1) // O
        game.makeMove(1, 0) // X
        game.makeMove(1, 1) // O
        game.makeMove(2, 0) // X wins column 0
        assertEquals(GameState.Won(Player.X), game.state)
    }

    @Test
    fun `three X in diagonal should win`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(0, 1) // O
        game.makeMove(1, 1) // X
        game.makeMove(0, 2) // O
        game.makeMove(2, 2) // X wins diagonal
        assertEquals(GameState.Won(Player.X), game.state)
    }

    @Test
    fun `three O in anti-diagonal should win`() {
        val game = Game()
        game.makeMove(1, 0) // X
        game.makeMove(0, 2) // O
        game.makeMove(0, 0) // X
        game.makeMove(1, 1) // O
        game.makeMove(2, 1) // X
        game.makeMove(2, 0) // O wins anti-diagonal
        assertEquals(GameState.Won(Player.O), game.state)
    }

    @Test
    fun `game in progress when no winner`() {
        val game = Game()
        game.makeMove(0, 0)
        assertEquals(GameState.InProgress, game.state)
    }

    @Test
    fun `full board with no winner should be draw`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(0, 1) // O
        game.makeMove(0, 2) // X
        game.makeMove(1, 1) // O
        game.makeMove(1, 0) // X
        game.makeMove(1, 2) // O
        game.makeMove(2, 1) // X
        game.makeMove(2, 0) // O
        game.makeMove(2, 2) // X
        assertEquals(GameState.Draw, game.state)
    }

    @Test
    fun `moves blocked after win`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(1, 0) // O
        game.makeMove(0, 1) // X
        game.makeMove(1, 1) // O
        game.makeMove(0, 2) // X wins
        val result = game.makeMove(2, 2)
        assertEquals(false, result)
    }

    @Test
    fun `moves blocked after draw`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(0, 1) // O
        game.makeMove(0, 2) // X
        game.makeMove(1, 1) // O
        game.makeMove(1, 0) // X
        game.makeMove(1, 2) // O
        game.makeMove(2, 1) // X
        game.makeMove(2, 0) // O
        game.makeMove(2, 2) // X - draw
    }

    @Test
    fun `reset should clear board`() {
        val game = Game()
        game.makeMove(0, 0)
        game.reset()
        assertEquals(null, game.getCell(0, 0))
    }

    @Test
    fun `reset should set current player to X`() {
        val game = Game()
        game.makeMove(0, 0)
        game.reset()
        assertEquals(Player.X, game.currentPlayer)
    }

    @Test
    fun `reset should set state to InProgress`() {
        val game = Game()
        game.makeMove(0, 0)
        game.makeMove(1, 0)
        game.makeMove(0, 1)
        game.makeMove(1, 1)
        game.makeMove(0, 2)
        game.reset()
        assertEquals(GameState.InProgress, game.state)
    }

    @Test
    fun `three X in bottom row should win`() {
        val game = Game()
        game.makeMove(2, 0) // X
        game.makeMove(0, 0) // O
        game.makeMove(2, 1) // X
        game.makeMove(0, 1) // O
        game.makeMove(2, 2) // X wins
        assertEquals(GameState.Won(Player.X), game.state)
    }

    @Test
    fun `three X in third column should win`() {
        val game = Game()
        game.makeMove(0, 2) // X
        game.makeMove(0, 0) // O
        game.makeMove(1, 2) // X
        game.makeMove(1, 0) // O
        game.makeMove(2, 2) // X wins
        assertEquals(GameState.Won(Player.X), game.state)
    }

    @Test
    fun `valid move should return true`() {
        val game = Game()
        val result = game.makeMove(0, 0)
        assertEquals(true, result)
    }

    @Test
    fun `getCell should return correct player`() {
        val game = Game()
        game.makeMove(0, 0)
        assertEquals(Player.X, game.getCell(0, 0))
    }

    @Test
    fun `getCell should return null for empty cell`() {
        val game = Game()
        assertEquals(null, game.getCell(1, 1))
    }

    @Test
    fun `O should win with middle row`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(1, 0) // O
        game.makeMove(2, 0) // X
        game.makeMove(1, 1) // O
        game.makeMove(2, 2) // X
        game.makeMove(1, 2) // O wins
        assertEquals(GameState.Won(Player.O), game.state)
    }

    @Test
    fun `winner should be identified correctly`() {
        val game = Game()
        game.makeMove(0, 0) // X
        game.makeMove(1, 0) // O
        game.makeMove(0, 1) // X
        game.makeMove(1, 1) // O
        game.makeMove(0, 2) // X wins
        val state = game.state
        assertTrue(state is GameState.Won)
        assertEquals(Player.X, (state as GameState.Won).winner)
    }

    @Test
    fun `move with negative row should be rejected`() {
        val game = Game()
        val result = game.makeMove(-1, 0)
        assertEquals(false, result)
    }

    @Test
    fun `move with row greater than 2 should be rejected`() {
        val game = Game()
        val result = game.makeMove(3, 0)
        assertEquals(false, result)
    }

    @Test
    fun `move with negative col should be rejected`() {
        val game = Game()
        val result = game.makeMove(0, -1)
        assertEquals(false, result)
    }

    @Test
    fun `move with col greater than 2 should be rejected`() {
        val game = Game()
        val result = game.makeMove(0, 3)
        assertEquals(false, result)
    }
}
