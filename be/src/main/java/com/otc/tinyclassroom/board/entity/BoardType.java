package com.otc.tinyclassroom.board.entity;

public enum BoardType {
    BOARD_NOTICE(1),
    BOARD_FREE(2),
    BOARD_CONCERN(3),
    BOARD_HABIT(4);

    private int boardType;

    private BoardType(int boardType) {
        this.boardType = boardType;
    }

    public int getBoardType() {
        return boardType;
    }
}
