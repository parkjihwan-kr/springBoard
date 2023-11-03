package com.pjh.board.Entity.User.dto;

import com.pjh.board.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String updateTitle;
    private String updateContents;

    public User toEntity() {
        return User.builder()
                .title(updateTitle)
                .contents(updateContents)
                .build();
    }

    // Getter 및 Setter 메소드
    // dto getter,setter필요함.
}
