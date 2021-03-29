package com.rafhael.barabas.apipayment.repositories;

import com.rafhael.barabas.apipayment.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
