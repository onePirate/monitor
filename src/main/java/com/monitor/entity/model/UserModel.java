package com.monitor.entity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

    private Integer id;

    private String username;

    private String password;

    private String role;
}
