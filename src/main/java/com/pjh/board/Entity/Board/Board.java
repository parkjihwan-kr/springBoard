package com.pjh.board.Entity.Board;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String username;

    private String password;

    private String contents;

    private LocalDateTime createdDate;

    @PrePersist			// db에 INSERT되기 직전에 실행
    public void createDate() {
        this.createdDate = LocalDateTime.now();
    }
}
