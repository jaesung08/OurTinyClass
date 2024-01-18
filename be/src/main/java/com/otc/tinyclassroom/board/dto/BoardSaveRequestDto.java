package com.otc.tinyclassroom.board.dto;

import com.otc.tinyclassroom.board.entity.Board;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * DTO for {@link Board}
 */

public record BoardSaveRequestDto(@NotNull String title, String content, Long writerId) implements Serializable {

}