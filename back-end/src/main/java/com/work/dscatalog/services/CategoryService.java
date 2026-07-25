package com.work.dscatalog.services;

import com.work.dscatalog.entities.Category;
import com.work.dscatalog.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //@Transactional(readOnly = true)
    public List<Category> findAll(){

        return repository.findAll();
    }
}
