package com.rafhael.barabas.apipayment.entities;

import com.rafhael.barabas.apipayment.data.vo.SaleProductVO;
import com.rafhael.barabas.apipayment.data.vo.SaleVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sales_products")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SaleProduct implements Serializable {

    public static final long serialVersionUID = 8775462951147595890L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sale")
    private Sale sale;

    public static SaleProduct create(SaleProductVO saleProductVO) {
        return new ModelMapper().map(saleProductVO, SaleProduct.class);
    }
}
