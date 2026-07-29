package com.work.dscatalog.dto;

import com.work.dscatalog.entities.Category;

import java.io.Serializable;
import java.util.Objects;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Long id;

    public CategoryDTO(){

    }

    public CategoryDTO(String name , long id){

        this.name = name;
        this.id = id;
    }

    public CategoryDTO(Category entity){


    }
    public String getName() {

        return name;
    }

    public long getId (){

        return id;
    }

    public void setName(String name){

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
