package com.example.demo.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UserDto {
    private Long id;
    private String username;
    private String email;
    
    public UserDto() {}

    public UserDto(Long id, String email, String username) {
        this.id = id;
        this.username = username;
        this.email = email;
    }
}
	