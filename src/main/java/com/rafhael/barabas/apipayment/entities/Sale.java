package com.rafhael.barabas.apipayment.entities;

import com.rafhael.barabas.apipayment.data.vo.SaleProductVO;
import com.rafhael.barabas.apipayment.data.vo.SaleVO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Sale implements Serializable {

    private static final long serialVersionUID = -4387639615616651612L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = {CascadeType.REFRESH})
    private List<SaleProduct> products;

    @Column(name = "total", nullable = false)
    private Double total;

    public static Sale create(SaleVO saleVO) {
        return new ModelMapper().map(saleVO, Sale.class);
    }

}
