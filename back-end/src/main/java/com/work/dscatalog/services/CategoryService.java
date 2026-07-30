package com.work.dscatalog.services;

import com.work.dscatalog.dto.CategoryDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.repositories.CategoryRepository;
import com.work.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){

        List <Category> list = repository.findAll();

        List <CategoryDTO> listDto  = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        return listDto;

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        //o optional serve para lidar com valores nulos
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow( () -> new EntityNotFoundException("Entity not found"));

        return new CategoryDTO(entity);

    }
}
