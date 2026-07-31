package com.work.dscatalog.services;

import com.work.dscatalog.dto.CategoryDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.repositories.CategoryRepository;
import com.work.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.work.dscatalog.services.exceptions.DatabaseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  CategoryService {

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
        Category entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));

        return new CategoryDTO(entity);

    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {

        Category entity = new Category();

        entity.setName(dto.getName());

        entity = repository.save(entity);

        return new CategoryDTO(entity);

    }


    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {

        try{
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());

            entity = repository.save(entity);

            return new CategoryDTO(entity);

        }catch (EntityNotFoundException e){

            throw  new ResourceNotFoundException("Id not found " + id);

        }

    }

    //tratamento de erro do delete

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete (Long id){

        if (!repository.existsById(id)){

            throw new ResourceNotFoundException("Resource not found");

        }
        try {

            repository.deleteById(id);

        }catch(DataIntegrityViolationException e){

            throw new DatabaseException("Integrity failure reference");
        }
    }
}
