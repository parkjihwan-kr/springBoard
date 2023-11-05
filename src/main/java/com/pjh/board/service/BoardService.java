package com.pjh.board.service;

import com.pjh.board.Entity.Board.Board;
import com.pjh.board.Entity.Board.BoardRepository;
import com.pjh.board.util.ApiRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional
    public Board 게시글작성(Board user){
        Board boardEntity = boardRepository.save(user);
        return boardEntity;
    }

    @Transactional
    public List<Board> 게시판모든리스트조회(){
        Sort sort = Sort.by(Sort.Order.desc("id"));
        // "id"를 기준으로 내림차순 정렬
        List<Board> boardList = boardRepository.findAll(sort);
        return boardList;
    }

    @Transactional
    public Board 게시글조회(int id){

        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + id));
    }

    @Transactional
    public Board 게시글수정(int id, Board user){

        Board userEntity = boardRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("게시글을 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        //System.out.println("userEntity.getPassword()"+userEntity.getPassword()+" user.getPassword() : "+user.getPassword());

        if (!userEntity.getPassword().equals(user.getPassword())) {
            throw new ApiRequestException("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
        userEntity.setTitle(user.getTitle());
        userEntity.setContents(user.getContents());
        user.setCreatedDate(user.getCreatedDate());
        boardRepository.save(userEntity);
        return userEntity;
    }
    @Transactional
    public void 게시글삭제(Board user, int id) {
        Optional<Board> userOptional = boardRepository.findById(id);

        if (userOptional.isPresent()) {
            Board userInDatabase = userOptional.get();
            String userPassword = userInDatabase.getPassword();

            if (userPassword.equals(user.getPassword())) {
                boardRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new EntityNotFoundException("해당 ID의 게시글을 찾을 수 없습니다: " + id);
        }
    }
}

