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
}
