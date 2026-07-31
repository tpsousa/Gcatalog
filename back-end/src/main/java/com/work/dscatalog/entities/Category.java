package com.work.dscatalog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

//camada de acesso a dados
@Entity
@Table (name = "tb_category")
public class Category implements Serializable {


    //lamboks sao abreviacoes para codigos repetitivos tipo getters e setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Setter
    @Getter
    private String name;

    public Category(){

    }

    public Category(long id , String name){
        this.id = id;
        this.name=  name;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
