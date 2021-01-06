package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	//Qdo eu crio a consulta e não uso a pronta
	@Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products "   //SELECT DISTINCT apelido FROM NomeDaClasse apelido JOIN FETCH apelido.nomeDaColeçãoSet
			+ " WHERE obj.status = 0 ORDER BY obj.moment ASC")   //Where apelido.nomeDaEnumeração = númEnumeração ORDER BY apelido.tempo ordem
	List<Order> findOrdersWithProducts();
	
}
