package com.pjh.board.controller;

import com.pjh.board.Entity.User.User;
import com.pjh.board.Entity.User.dto.UserPostDto;
import com.pjh.board.Entity.User.dto.UserUpdateDto;
import com.pjh.board.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String writePost(@ModelAttribute UserPostDto userPostDto) {
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
    public User showUpdateDetails(@PathVariable int id, @RequestBody UserUpdateDto userUpdateDto){
        return userService.게시글수정(userUpdateDto.toEntity(), id);
    }
}
