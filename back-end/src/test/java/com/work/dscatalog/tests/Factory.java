package com.work.dscatalog.tests;

import com.work.dscatalog.dto.ProductDTO;
import com.work.dscatalog.entities.Category;
import com.work.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct() {

        return new Product(1L, "Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-03-12T10:00:00Z"));


    }
    public static ProductDTO createProductDTO () {

        Product product = createProduct();

        return new ProductDTO(product, product.getCategories());


    }
}
