package com.otc.tinyclassroom.board.entity.type;

public enum SearchType {
    TITLE, CONTENT, NAME, TITLE_CONTENT;

    // 대소문자 구별 없이 받아주기 위한 함수
    public static SearchType fromString(String text) {
        for (SearchType type : SearchType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
