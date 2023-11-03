package com.pjh.board.controller.apiController;


import com.pjh.board.Entity.User.User;
import com.pjh.board.Entity.User.dto.UserDeleteDto;
import com.pjh.board.Entity.User.dto.UserPostDto;
import com.pjh.board.Entity.User.dto.UserUpdateDto;
import com.pjh.board.service.UserService;
import com.pjh.board.util.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String writePost(@RequestBody UserPostDto userPostDto) {
        User user = userService.게시글작성(userPostDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public User showDetails(@PathVariable int id){
        return userService.게시글조회(id);
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<?> showUpdateDetails(
            @PathVariable int id,
            @RequestBody UserUpdateDto userUpdateDto){
        try {
            User updatedUser = userService.게시글수정(id, userUpdateDto.toEntity());
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ApiRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> showDeleteDetails(
            @PathVariable int id,
            @RequestBody UserDeleteDto userDeleteDto){
        try {
            userService.게시글삭제(userDeleteDto.toEntity(), id);
            return new ResponseEntity<>("게시물이 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } catch (ApiRequestException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        }
    }
}
