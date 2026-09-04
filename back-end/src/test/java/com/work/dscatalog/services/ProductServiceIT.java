package com.work.dscatalog.services;


import com.work.dscatalog.dto.ProductDTO;
import com.work.dscatalog.entities.Product;
import com.work.dscatalog.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceIT {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @Test
    void findByIdShouldReturnProductDTOWhenIdExists(){

        Product product = repository.findById(1L).orElseThrow();

        ProductDTO result = service.findById(1L);

        assertThat(result).isNotNull();

        assertThat(result.getId()).isEqualTo(product.getId());

        assertThat(result.getName()).isEqualTo(product.getName());

    }

}
