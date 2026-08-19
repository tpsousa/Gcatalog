
package com.work.dscatalog.repositories;

import com.work.dscatalog.entities.Product;
import com.work.dscatalog.repositories.ProductRepository;
import com.work.dscatalog.services.ProductService;
import com.work.dscatalog.services.exceptions.DatabaseException;
import com.work.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
// Da aula
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long nonExistinhId;
    private long countTotalProducts;
    private PageImpl<Product> page;

    private Product product;

    @BeforeEach
    void setUp() throws Exception{

        existingId = 1L;
        nonExistinhId= 1000L;
        countTotalProducts = 52L;
        page = new PageImpl<>(List.of(product));

        Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));

        Mockito.when(repository.findById(nonExistinhId)).thenReturn(Optional.empty());
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

