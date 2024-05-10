package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserGetResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    public List<UserGetResponse> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserGetResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName());
        userRepository.save(user);
    }

    public void deleteUser(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}
