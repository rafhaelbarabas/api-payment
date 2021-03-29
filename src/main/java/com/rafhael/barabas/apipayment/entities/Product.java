package com.rafhael.barabas.apipayment.entities;

import com.rafhael.barabas.apipayment.data.vo.ProductVO;
import com.rafhael.barabas.apipayment.data.vo.SaleProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public static final long serialVersionUID = 473367310035748454L;

    @Id
    private Long id;

    @Column(name = "inventory", nullable = false)
    private Integer inventory;

    public static Product create(ProductVO productVO) {
        return new ModelMapper().map(productVO, Product.class);
    }
}
