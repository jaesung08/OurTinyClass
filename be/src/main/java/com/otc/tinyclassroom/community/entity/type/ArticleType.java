package com.otc.tinyclassroom.community.entity.type;

public enum ArticleType {
    NOTICE, FREE, COUNSELING, HOBBY;

    // 대소문자 구별 없이 받아주기 위한 함수
    public static ArticleType fromString(String text) {
        for (ArticleType type : ArticleType.values()) {
            if (type.name().equalsIgnoreCase(text)) {
                return type;
            }
        }
        return null;
    }
}
