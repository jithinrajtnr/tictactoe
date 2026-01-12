package com.dev2_002.tictactoe.domain

import org.junit.Assert.assertEquals
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
}
