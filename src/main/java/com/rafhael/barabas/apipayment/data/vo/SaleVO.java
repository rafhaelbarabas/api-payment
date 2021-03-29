package com.rafhael.barabas.apipayment.data.vo;

import com.rafhael.barabas.apipayment.entities.Sale;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SaleVO extends RepresentationModel<SaleVO> implements Serializable {

    public static final long serialVersionUID = 4930360964580937772L;

    private Long id;
    private Date date;
    private List<SaleProductVO> products;
    private Double total;

    public static SaleVO create(Sale sale) {
        return new ModelMapper().map(sale, SaleVO.class);
    }
}
