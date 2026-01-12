package com.dev2_002.tictactoe.ui

import com.dev2_002.tictactoe.domain.Game
import com.dev2_002.tictactoe.domain.GameState
import com.dev2_002.tictactoe.domain.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel {
    private val game = Game()

    private val _uiState = MutableStateFlow(createUiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun onCellClick(row: Int, col: Int) {
        game.makeMove(row, col)
        _uiState.value = createUiState()
    }

    fun onReset() {
        game.reset()
        _uiState.value = createUiState()
    }

    private fun createUiState(): UiState {
        val cells = (0..2).map { row ->
            (0..2).map { col -> game.getCell(row, col) }
        }
        return UiState(
            cells = cells,
            currentPlayer = game.currentPlayer,
            gameState = game.state
        )
    }
}
