package com.dev2_002.tictactoe.domain

sealed class GameState {
    object InProgress : GameState()
    data class Won(val winner: Player) : GameState()
    object Draw : GameState()
}
