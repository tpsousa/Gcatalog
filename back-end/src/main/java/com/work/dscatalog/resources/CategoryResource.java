package com.work.dscatalog.resources;

import com.work.dscatalog.dto.CategoryDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.services.CategoryService;
import com.work.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<CategoryDTO> insert (@RequestBody CategoryDTO dto){

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);


    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update (@PathVariable Long id , @RequestBody CategoryDTO dto){

        dto = service.update(id,dto);

        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){

        service.delete(id);

        dto = service.update(id,dto);

        return ResponseEntity.noContent().build();

    }
}

