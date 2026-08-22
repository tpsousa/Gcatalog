package com.work.dscatalog.services;

import com.work.dscatalog.dto.ProductDTO;
import com.work.dscatalog.entities.Product;
import com.work.dscatalog.repositories.ProductRepository;
import com.work.dscatalog.services.exceptions.DatabaseException;
import com.work.dscatalog.services.exceptions.ResourceNotFoundException;
import com.work.dscatalog.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {


    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    private long existingId;
    private long nonExistingId;
    //private Product product;


    private Product product;

    private Long dependentId;

    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;

        nonExistingId = 1000L;

        dependentId = 4L;

        product = Factory.createProduct();

        //product = Factory.createProduct();

        //Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));

        //Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());


    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {
        // configura SÓ o que esse teste precisa, aqui dentro
        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(existingId);

        Assertions.assertDoesNotThrow(() -> service.delete(existingId));
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        // configura SÓ o que esse teste precisa
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingId));
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
        // configura SÓ o que esse teste precisa
        Mockito.when(repository.existsById(dependentId)).thenReturn(true);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

        Assertions.assertThrows(DatabaseException.class, () -> service.delete(dependentId));
    }

    @Test
    public void findAllPagedShouldReturnPage(){

       Pageable pageable = PageRequest.of(0,10);

       Page<ProductDTO> result = service.findAllPaged(pageable);

       Assertions.assertNotNull(result);

       Mockito.verify(repository, Mockito.times(1)).findAll(pageable);

    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists(){

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));

        ProductDTO result = service.findById(existingId);

        Assertions.assertNotNull(result);

        Assertions.assertEquals(existingId, result.getId());
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() {
        Mockito.when(repository.getReferenceById(existingId)).thenReturn(product);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        ProductDTO result = service.update(existingId, Factory.createProductDTO());

        Assertions.assertNotNull(result);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, Factory.createProductDTO());
        });
    }
}
