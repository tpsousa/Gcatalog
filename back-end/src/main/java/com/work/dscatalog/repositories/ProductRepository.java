package com.work.dscatalog.repositories;

import com.work.dscatalog.entities.Category;
import com.work.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long>  {


}
