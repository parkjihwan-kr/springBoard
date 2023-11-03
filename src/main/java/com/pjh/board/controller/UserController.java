package com.pjh.board.controller;

import com.pjh.board.Entity.User.User;
import com.pjh.board.Entity.User.dto.UserDeleteDto;
import com.pjh.board.Entity.User.dto.UserPostDto;
import com.pjh.board.Entity.User.dto.UserUpdateDto;
import com.pjh.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    //private final BoardService boardService;

    @GetMapping({"/","/board/index"})
    public String showMainPage(Model model){
        List<User> boardList = userService.게시판모든리스트조회();
        model.addAttribute("boardList", boardList);
        return "board/index";
    }

    @PostMapping("/api/user")
    public String writePost(@RequestBody UserPostDto userPostDto) {
        User user = userService.게시글작성(userPostDto.toEntity());
        return "redirect:/";
    }

    @GetMapping("/api/user/{id}")
    @ResponseBody
    public User showDetails(@PathVariable int id){
        return userService.게시글조회(id);
    }

    @PutMapping("/api/user/{id}")
    @ResponseBody
    public User showUpdateDetails(
            @PathVariable int id,
            @RequestBody UserUpdateDto userUpdateDto){
        return userService.게시글수정(id, userUpdateDto.toEntity());
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> showDeleteDetails(
            @PathVariable int id,
            @RequestBody UserDeleteDto userDeleteDto,
            Model model){
        String message = userService.게시글삭제(userDeleteDto.toEntity(), id);
        model.addAttribute("message", message);
        if (message.equals("게시글 삭제에 성공하셨습니다")) {
            return new ResponseEntity<>("게시글이 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("게시글 삭제에 실패했습니다. 유효한 비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST);
        }
    }
}

/*
- [ ]  게시글 작성 기능
    - `제목`, `작성자명`, `비밀번호`, `작성 내용`, `작성일`을 저장할 수 있습니다.
    - 저장된 게시글의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- [ ]  선택한 게시글 조회 기능
    - 선택한 게시글의 정보를 조회할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- [ ]  게시글 목록 조회 기능
    - 등록된 게시글 전체를 조회할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
    - 조회된 게시글 목록은 작성일 기준 내림차순으로 정렬 되어있습니다.
- [ ]  선택한 게시글 수정 기능
    - 선택한 게시글의 `제목`, `작성자명`, `작성 내용`을 수정할 수 있습니다.
        - 서버에 게시글 수정을 요청할 때 `비밀번호`를 함께 전달합니다.
        - 선택한 게시글의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 수정이 가능합니다.
    - 수정된 게시글의 정보를 반환 받아 확인할 수 있습니다.
        - 반환 받은 게시글의 정보에 `비밀번호`는 제외 되어있습니다.
- [ ]  선택한 게시글 삭제 기능
    - 선택한 게시글을 삭제할 수 있습니다.
        - 서버에 게시글 삭제를 요청할 때 `비밀번호`를 함께 전달합니다.
        - 선택한 게시글의 `비밀번호`와 요청할 때 함께 보낸 `비밀번호`가 일치할 경우에만 삭제가 가능합니다.*/
