    package com.pjh.board.service;

    import com.pjh.board.Entity.User.User;
    import com.pjh.board.Entity.User.UserRepository;
    import lombok.RequiredArgsConstructor;
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

            /*UUID uuid = UUID.randomUUID();
            String rawPassword = user.getPassword();
            String uuidPassword = uuid+rawPassword;
            user.setPassword(uuidPassword);*/
            User userEntity = userRepository.save(user);
            return userEntity;
        }

        @Transactional
        public List<User> 게시판모든리스트조회(){
            List<User> userLists = userRepository.findAll();
            userLists.stream().forEach((userList)->{
                /*System.out.println(
                    "userList getName : "+userList.getUsername()
                            +" userList getPassword : "+userList.getPassword()
                            +" userList getTitle : "+userList.getTitle()
                            +" userList getContents : "+userList.getContents()
                            +" userList getCreatedTime : "+userList.getCreatedDate()
                    +" userLists : "+userLists.size()
                );*/
            });
            return userLists;
        }

        @Transactional
        public User 게시글조회(int id){
            User user = new User();
            user = userRepository.findById(id).get();
            return user;
        }

        @Transactional
        public User 게시글수정(User user, int id){
            User userEntity = userRepository.findById(id).get();
            userEntity.setTitle(user.getTitle());
            userEntity.setContents(user.getContents());
            return userEntity;
        }
    }
