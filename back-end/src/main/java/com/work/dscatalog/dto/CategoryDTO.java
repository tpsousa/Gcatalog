package com.work.dscatalog.dto;

import com.work.dscatalog.entities.Category;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Setter
    private String name;
    private Long id;

    public CategoryDTO(){

    }

    public CategoryDTO(String name , long id){

        this.name = name;
        this.id = id;
    }

    //construtor de conversao, pega uma entidade category e transforma em um categoryDto
    //que e o objeto que queremos desolver na API.
    public CategoryDTO(Category entity){

        id = entity.getId();
        name = entity.getName();

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
