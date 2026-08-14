package com.work.dscatalog.services;

import com.work.dscatalog.entities.Product;
import com.work.dscatalog.repositories.ProductRepository;
import com.work.dscatalog.services.exceptions.DatabaseException;
import com.work.dscatalog.services.exceptions.ResourceNotFoundException;
import com.work.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
