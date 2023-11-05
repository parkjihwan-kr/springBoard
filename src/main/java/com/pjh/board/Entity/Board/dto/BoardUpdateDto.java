package com.pjh.board.Entity.Board.dto;

import com.pjh.board.Entity.Board.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateDto {
    private String updateTitle;
    private String updatePassword;
    private String updateUsername;
    private String updateContents;

    public Board toEntity() {
        return Board.builder()
                .username(updateUsername)
                .password(updatePassword)
                .title(updateTitle)
                .contents(updateContents)
                .build();
    }

    // Getter 및 Setter 메소드
    // dto getter,setter필요함.
}
