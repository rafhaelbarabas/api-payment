package com.rafhael.barabas.apipayment.data.vo;

import com.rafhael.barabas.apipayment.entities.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO extends RepresentationModel<SaleVO> implements Serializable {

    public static final long serialVersionUID = 5507872939876407829L;

    private Long id;
    private Integer inventory;

    public static ProductVO create(Product product) {
        return new ModelMapper().map(product, ProductVO.class);
    }
}
