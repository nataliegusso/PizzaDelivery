package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service	//Ou @Component - registro do componente no sistema do spring boot
public class ProductService {

	@Autowired   //Faz a injeção de independência. Não precisa ser da forma descrita abaixo.
	private ProductRepository repository;

//	public ProductService(ProductRepository repository) {   //Injeção de independência
//		this.repository = repository;
//	}
	
	@Transactional(readOnly = true)  //garante a transação e é só leitura
	public List<ProductDTO> findAll(){  //mas ele não retorna p front end um produto, ele retorna um DTO
										//ProductDTO - classe que faz a conversão das entidades em DTO
		List<Product> list = repository.findAllByOrderByNameAsc();   //Busca os produtos do BD
		//return list.stream().map(x -> new ProductDTO(x))   //Transforma cada elemento do produt em DTO
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());   //retorna a stream para uma lista
	}
}
