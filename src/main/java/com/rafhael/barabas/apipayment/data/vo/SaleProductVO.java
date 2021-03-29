package com.rafhael.barabas.apipayment.data.vo;

import com.rafhael.barabas.apipayment.entities.Sale;
import com.rafhael.barabas.apipayment.entities.SaleProduct;
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
public class SaleProductVO extends RepresentationModel<SaleProductVO> implements Serializable {

    public static final long serialVersionUID = 5398366465208968000L;

    private Long id;
    private Long idProduct;
    private Integer quantity;

    public static SaleProductVO create(SaleProduct saleProduct) {
        return new ModelMapper().map(saleProduct, SaleProductVO.class);
    }
}
