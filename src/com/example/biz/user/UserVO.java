package com.example.biz.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
    private String id;
    private String password;
    private String name;
    private String role;
}
