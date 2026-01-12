package com.dev2_002.tictactoe.ui

import com.dev2_002.tictactoe.domain.GameState
import com.dev2_002.tictactoe.domain.Player
import org.junit.Assert.assertEquals
import org.junit.Test

class GameViewModelTest {

    @Test
    fun `initial state should have empty board`() {
        val viewModel = GameViewModel()
        val state = viewModel.uiState.value
        assertEquals(9, state.cells.flatten().count { it == null })
    }

    @Test
    fun `initial state should have X as current player`() {
        val viewModel = GameViewModel()
        assertEquals(Player.X, viewModel.uiState.value.currentPlayer)
    }

    @Test
    fun `clicking cell should place mark`() {
        val viewModel = GameViewModel()
        viewModel.onCellClick(0, 0)
        assertEquals(Player.X, viewModel.uiState.value.cells[0][0])
    }

    @Test
    fun `clicking cell should alternate player`() {
        val viewModel = GameViewModel()
        viewModel.onCellClick(0, 0)
        assertEquals(Player.O, viewModel.uiState.value.currentPlayer)
    }

    @Test
    fun `reset should clear board`() {
        val viewModel = GameViewModel()
        viewModel.onCellClick(0, 0)
        viewModel.onReset()
        assertEquals(null, viewModel.uiState.value.cells[0][0])
    }

    @Test
    fun `win should update game state`() {
        val viewModel = GameViewModel()
        viewModel.onCellClick(0, 0) // X
        viewModel.onCellClick(1, 0) // O
        viewModel.onCellClick(0, 1) // X
        viewModel.onCellClick(1, 1) // O
        viewModel.onCellClick(0, 2) // X wins
        assertEquals(GameState.Won(Player.X), viewModel.uiState.value.gameState)
    }
}
