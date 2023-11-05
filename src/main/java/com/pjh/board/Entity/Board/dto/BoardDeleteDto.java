package com.pjh.board.Entity.Board.dto;

import com.pjh.board.Entity.Board.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDeleteDto {
    String deletePassword;

    public Board toEntity(){
        return Board.builder()
                .password(deletePassword)
                .build();
    }
}
