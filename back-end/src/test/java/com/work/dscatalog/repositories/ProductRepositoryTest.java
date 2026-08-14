
package com.work.dscatalog.repositories;

import com.work.dscatalog.entities.Product;
import com.work.dscatalog.repositories.ProductRepository;
import com.work.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// Da aula
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistinhId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception{

        existingId = 1L;
        nonExistinhId= 1000L;
        countTotalProducts = 52L;

    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull (){

        Product product = Factory.createProduct();
        product.setId(null);

        product  = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }


    @Test
    public void findByIdShouldReturnOptionalEmptyWhenIdDoesNotExist() {

        Optional <Product> result = repository.findById(nonExistinhId);

        Assertions.assertFalse(result.isPresent());

    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists (){

        Optional<Product> result = repository.findById(existingId);

        Assertions.assertTrue(result.isPresent());
    }
}

