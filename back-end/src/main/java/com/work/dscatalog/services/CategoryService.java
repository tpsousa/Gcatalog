package com.work.dscatalog.services;

import com.work.dscatalog.entities.Category;
import com.work.dscatalog.repositories.CategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public List<Category> finAll(){

        return repository.findAll()
    }
}
