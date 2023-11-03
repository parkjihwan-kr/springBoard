package com.pjh.board.apiController;


import com.pjh.board.Entity.User.User;
import com.pjh.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final UserService userService;

    /*@GetMapping("/api/user/{id}")
    public User showDetails(@PathVariable int id){
        return userService.게시글조회(id);
    }*/
}
