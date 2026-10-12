package com.work.dscatalog.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String password;

    private Set<Role> roles = new HashSet<>();

    public User () {

    }

    public User (Long id , String firstName, String lastName,String password) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;

        this.password = password;
    }


}
