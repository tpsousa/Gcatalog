package com.work.dscatalog.resources;

import com.work.dscatalog.dto.CategoryDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
//esse category resource e responsavel por todas as rotas que nos criamos na aplicacao
//relacionados ao category,e aqui que nos temos as nossas rotas.

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List <CategoryDTO>> findAll(){

        List<CategoryDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){

        CategoryDTO dto = service.findById(id);

        return ResponseEntity.ok().body(dto);

    }
}
