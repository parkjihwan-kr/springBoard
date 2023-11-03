package com.pjh.board.Entity.User.dto;

import com.pjh.board.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
    private int id;
    private String title;
    private String password;
    private String username;
    private String contents;
    private String createdDate;

    public User toEntity(){
        return User.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .username(username)
                .password(password)
                .build();
    }
}
