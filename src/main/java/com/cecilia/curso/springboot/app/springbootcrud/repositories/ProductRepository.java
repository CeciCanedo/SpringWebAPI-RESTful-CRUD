package com.cecilia.curso.springboot.app.springbootcrud.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cecilia.curso.springboot.app.springbootcrud.entities.Product;


public interface ProductRepository extends CrudRepository<Product, Long>{

}
