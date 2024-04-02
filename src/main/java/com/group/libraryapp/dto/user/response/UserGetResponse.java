package com.group.libraryapp.dto.user.response;

import com.group.libraryapp.domain.user.User;

public class UserGetResponse {

    private long id;
    private String name;
    private Integer age;

    public UserGetResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
