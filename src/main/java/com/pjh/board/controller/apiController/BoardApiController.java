package com.pjh.board.controller.apiController;


import com.pjh.board.Entity.Board.Board;
import com.pjh.board.Entity.Board.dto.BoardDeleteDto;
import com.pjh.board.Entity.Board.dto.BoardPostDto;
import com.pjh.board.Entity.Board.dto.BoardUpdateDto;
import com.pjh.board.service.BoardService;
import com.pjh.board.util.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/user")
    public String writePost(@RequestBody BoardPostDto userPostDto) {
        Board board = boardService.게시글작성(userPostDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    //@ResponseBody
    public Board showDetails(@PathVariable int id){
        return boardService.게시글조회(id);
    }

    @PutMapping("/user/{id}")
    //@ResponseBody
    public ResponseEntity<?> updatePost(
            @PathVariable int id,
            @RequestBody BoardUpdateDto boardUpdateDto){
        try {
            Board updatedUser = boardService.게시글수정(id, boardUpdateDto.toEntity());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deletePost(
            @PathVariable int id,
            @RequestBody BoardDeleteDto boardDeleteDto){
        try {
            boardService.게시글삭제(boardDeleteDto.toEntity(), id);
            return new ResponseEntity<>("게시물이 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } catch (ApiRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }
}
