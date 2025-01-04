package com.untouchable.everytime.article.service.data

import com.untouchable.everytime.article.domain.Board
import com.untouchable.everytime.article.domain.BoardType

data class BoardData(
    val id: Long = 0L,
    var name: String,
    var description: String,
    val boardType: BoardType,
) {
    companion object {
        fun from(board: Board): BoardData {
            return BoardData(
                id = board.id,
                name = board.name,
                description = board.description,
                boardType = board.boardType,
            )
        }
    }
}
