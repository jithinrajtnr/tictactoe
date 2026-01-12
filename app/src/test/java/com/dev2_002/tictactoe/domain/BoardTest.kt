package com.dev2_002.tictactoe.domain

import org.junit.Assert.*
import org.junit.Test

class BoardTest {

    @Test
    fun `new board should be empty`() {
        val board = Board()
        for (row in 0..2) {
            for (col in 0..2) {
                assertNull(board.getCell(row, col))
            }
        }
    }

    @Test
    fun `setCell should place player`() {
        val board = Board()
        board.setCell(0, 0, Player.X)
        assertEquals(Player.X, board.getCell(0, 0))
    }

    @Test
    fun `isFull should return false for empty board`() {
        val board = Board()
        assertFalse(board.isFull())
    }

    @Test
    fun `isFull should return true for full board`() {
        val board = Board()
        for (row in 0..2) {
            for (col in 0..2) {
                board.setCell(row, col, Player.X)
            }
        }
        assertTrue(board.isFull())
    }

    @Test
    fun `clear should empty the board`() {
        val board = Board()
        board.setCell(0, 0, Player.X)
        board.clear()
        assertNull(board.getCell(0, 0))
    }

    @Test
    fun `setCell with O should place O`() {
        val board = Board()
        board.setCell(1, 1, Player.O)
        assertEquals(Player.O, board.getCell(1, 1))
    }

    @Test
    fun `partial board should not be full`() {
        val board = Board()
        board.setCell(0, 0, Player.X)
        board.setCell(1, 1, Player.O)
        assertFalse(board.isFull())
    }
}
