package com.work.dscatalog.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String authority;

    public Role (){

    }

    public Role (Long id, String authority){

        this.id = id;
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(authority, role.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority);
    }
}
