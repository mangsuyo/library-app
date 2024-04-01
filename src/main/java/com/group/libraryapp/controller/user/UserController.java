package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserGetResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    List<User> users = new ArrayList<>();

    @PostMapping("/user")
    public void createUser(@RequestBody UserCreateRequest userCreateRequest){
        users.add(new User(userCreateRequest.getName(), userCreateRequest.getAge()));
    }

    @GetMapping("/user")
    public List<UserGetResponse> getUsers(){
        List<UserGetResponse> responses = new ArrayList<>();
        for(int i = 0; i < users.size(); i++){
            responses.add(new UserGetResponse(i + 1, users.get(i)));
        }
        return responses;
    }

}
