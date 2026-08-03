package com.work.dscatalog.resources;

import com.work.dscatalog.dto.ProductDTO;
import com.work.dscatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
//esse Product resource e responsavel por todas as rotas que nos criamos na aplicacao
//relacionados ao Product,e aqui que nos temos as nossas rotas.

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(

           Pageable pageable ){


       // PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction) , orderBy);

        Page<ProductDTO> list = service.findAllPaged(pageable);

        return ResponseEntity.ok().body(list);

    }
    @GetMapping(value = "/{id}")
    //path variable e quando o parametro e obrigatorio
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){

        ProductDTO dto = service.findById(id);

        return ResponseEntity.ok().body(dto);

    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert (@RequestBody ProductDTO dto){

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);


    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update (@PathVariable Long id , @RequestBody ProductDTO dto){

        dto = service.update(id,dto);

        return ResponseEntity.ok().body(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){

        service.delete(id);

        //dto = service.update(id,update);

        return ResponseEntity.noContent().build();

    }
}

