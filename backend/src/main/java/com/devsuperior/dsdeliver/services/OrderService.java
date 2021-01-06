package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired  
	private OrderRepository repository;
	
	@Autowired  
	private ProductRepository productRepository;
		
	@Transactional(readOnly = true)  //consulta no BD
	public List<OrderDTO> findAll() {  

		List<Order> list = repository.findOrdersWithProducts(); 
		
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());   
	}
	
	@Transactional  //alteração no BD
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {   //para associar as ordens e os produtos delas
			Product product = productRepository.getOne(p.getId());   //getOne instancia sem precisar ir no BD - cria as relações order e product
			order.getProducts().add(product);   // intanciar uma entidade correspondente a cada dto
		}
		order = repository.save(order);   //salvar no BD
		return new OrderDTO(order);
	}
	
	@Transactional  //alteração no BD
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);  //instanciei sem mexer no BD
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);   //agora que salva no BD
		return new OrderDTO(order);
	}
}
