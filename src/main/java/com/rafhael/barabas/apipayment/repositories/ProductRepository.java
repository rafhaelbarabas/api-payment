package com.rafhael.barabas.apipayment.repositories;

import com.rafhael.barabas.apipayment.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
