package com.pjh.board.Entity.User.dto;

import com.pjh.board.Entity.User.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteDto {
    String deletePassword;

    public User toEntity(){
        return User.builder()
                .password(deletePassword)
                .build();
    }
}
