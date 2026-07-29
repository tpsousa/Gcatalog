package com.work.dscatalog.services;

import com.work.dscatalog.dto.CategoryDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.repositories.CategoryRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
