package com.otc.tinyclassroom.community.entity.type;

/**
 * 조회 타입.
 */
public enum SearchType {
    TITLE, CONTENT, NAME, TITLE_CONTENT;

    /**
     * 대소문자 구별 없이 받아주기 위한 함수.
      */
    public static SearchType fromString(String text) {
        for (SearchType type : SearchType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}