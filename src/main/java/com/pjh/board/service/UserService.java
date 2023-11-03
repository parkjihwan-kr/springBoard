package com.pjh.board.service;

import com.pjh.board.Entity.User.User;
import com.pjh.board.Entity.User.UserRepository;
import com.pjh.board.util.ApiRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public User 게시글작성(User user){
        User userEntity = userRepository.save(user);
        return userEntity;
    }

    @Transactional
    public List<User> 게시판모든리스트조회(){
        Sort sort = Sort.by(Sort.Order.desc("id"));
        // "id"를 기준으로 내림차순 정렬
        List<User> userLists = userRepository.findAll(sort);
        /*userLists.stream().forEach((userList)->{
            System.out.println(
                "userList getName : "+userList.getUsername()
                        +" userList getPassword : "+userList.getPassword()
                        +" userList getTitle : "+userList.getTitle()
                        +" userList getContents : "+userList.getContents()
                        +" userList getCreatedTime : "+userList.getCreatedDate()
                +" userLists : "+userLists.size()
            );
        });*/
        return userLists;
    }

    @Transactional
    public User 게시글조회(int id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + id));
    }

    @Transactional
    public User 게시글수정(int id, User user){

        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        //System.out.println("userEntity.getPassword()"+userEntity.getPassword()+" user.getPassword() : "+user.getPassword());

        if (!userEntity.getPassword().equals(user.getPassword())) {
            throw new ApiRequestException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
        userEntity.setTitle(user.getTitle());
        userEntity.setContents(user.getContents());
        user.setCreatedDate(user.getCreatedDate());
        userRepository.save(userEntity);
        return userEntity;
    }
    @Transactional
    public void 게시글삭제(User user, int id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userInDatabase = userOptional.get();
            String userPassword = userInDatabase.getPassword();

            if (userPassword.equals(user.getPassword())) {
                userRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + id);
        }
    }
}

