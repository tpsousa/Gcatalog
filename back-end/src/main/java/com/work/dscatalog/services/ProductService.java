package com.work.dscatalog.services;

import com.work.dscatalog.dto.CategoryDTO;
import com.work.dscatalog.dto.ProductDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.entities.Product;
import com.work.dscatalog.repositories.CategoryRepository;
import com.work.dscatalog.repositories.ProductRepository;
import com.work.dscatalog.services.exceptions.DatabaseException;
import com.work.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable){

        Page <Product> list = repository.findAll(pageable);

        Page <ProductDTO> listDto  = list.map(x -> new ProductDTO(x));

        return listDto;

    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        //o optional serve para lidar com valores nulos
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow( () -> new ResourceNotFoundException("Entity not found"));

        return new ProductDTO(entity,entity.getCategories());

    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDate(dto.getDate());

        entity.getCategories().clear();
        for (CategoryDTO catDto : dto.getCategories()) {
            Category category = categoryRepository.getReferenceById(catDto.getId());
            entity.getCategories().add(category);
        }
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {

        Product entity = new Product();

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);

        return new ProductDTO(entity);

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {

        try{
            Product entity = repository.getReferenceById(id);

            copyDtoToEntity(dto, entity);

            entity = repository.save(entity);

            return new ProductDTO(entity);

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
