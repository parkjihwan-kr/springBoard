package com.pjh.board.service;

import com.pjh.board.Entity.User.User;
import com.pjh.board.Entity.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final UserRepository userRepository;

    /*public void 게시판모든리스트조회(int id){
        Optional<User> userLists = userRepository.findById(id);
        userLists.stream().forEach((userList)-> {
            System.out.println("userListId"+userList.getId());
        });
    }*/
}
