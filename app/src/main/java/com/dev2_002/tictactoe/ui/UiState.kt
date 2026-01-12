package com.dev2_002.tictactoe.ui

import com.dev2_002.tictactoe.domain.GameState
import com.dev2_002.tictactoe.domain.Player

data class UiState(
    val cells: List<List<Player?>>,
    val currentPlayer: Player,
    val gameState: GameState
)
