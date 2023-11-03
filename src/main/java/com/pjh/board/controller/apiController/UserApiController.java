package com.pjh.board.controller.apiController;


import com.pjh.board.Entity.User.User;
import com.pjh.board.Entity.User.dto.UserDeleteDto;
import com.pjh.board.Entity.User.dto.UserPostDto;
import com.pjh.board.Entity.User.dto.UserUpdateDto;
import com.pjh.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public User showUpdateDetails(
            @PathVariable int id,
            @RequestBody UserUpdateDto userUpdateDto){
        return userService.게시글수정(id, userUpdateDto.toEntity());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> showDeleteDetails(
            @PathVariable int id,
            @RequestBody UserDeleteDto userDeleteDto){
        userService.게시글삭제(userDeleteDto.toEntity(), id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
