package com.pjh.board.Entity.User.dto;

import com.pjh.board.Entity.User.User;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

public class UserUpdateDto {
    private String title;
    private String contents;

    public User toEntity(){
        return User.builder()
                .title(title)
                .contents(contents)
                .build();
    }
}
