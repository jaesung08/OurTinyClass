package com.otc.tinyclassroom.lecture.entity.type;

import lombok.Getter;

/**
 * 강의의 분류 카테고리를 위한 enum class 정의.
 */
@Getter
public enum LectureCategoryType {
    NO_CATEGORY, KOREAN, MATH, SCIENCE, ENGLISH, MUSIC, ART, ETHICS, HOME_ECONOMICS, CAREER, HOBBY;
}
