package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {  //Facilita a consulta ao BD. Já vem com CRUD padronizado

	List<Product> findAllByOrderByNameAsc();
}
