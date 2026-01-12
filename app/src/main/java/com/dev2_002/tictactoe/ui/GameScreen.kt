package com.dev2_002.tictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev2_002.tictactoe.domain.GameState
import com.dev2_002.tictactoe.domain.Player

@Composable
fun GameScreen(viewModel: GameViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StatusText(uiState)
        Spacer(modifier = Modifier.height(24.dp))
        Board(uiState.cells) { row, col ->
            viewModel.onCellClick(row, col)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { viewModel.onReset() }) {
            Text("Reset")
        }
    }
}

@Composable
private fun StatusText(uiState: UiState) {
    val text = when (val state = uiState.gameState) {
        is GameState.InProgress -> "Turn: ${uiState.currentPlayer}"
        is GameState.Won -> "Winner: ${state.winner}"
        is GameState.Draw -> "Draw"
    }
    Text(
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Board(
    cells: List<List<Player?>>,
    onCellClick: (Int, Int) -> Unit
) {
    Column {
        for (row in 0..2) {
            Row {
                for (col in 0..2) {
                    Cell(
                        player = cells[row][col],
                        onClick = { onCellClick(row, col) }
                    )
                }
            }
        }
    }
}

@Composable
private fun Cell(
    player: Player?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .border(1.dp, Color.Black)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = player?.name ?: "",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = if (player == Player.X) Color.Blue else Color.Red
        )
    }
}
