package com.rafhael.barabas.apipayment.repositories;

import com.rafhael.barabas.apipayment.entities.SaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct, Long> {
}
