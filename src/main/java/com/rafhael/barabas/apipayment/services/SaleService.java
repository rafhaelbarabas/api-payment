package com.rafhael.barabas.apipayment.services;

import com.rafhael.barabas.apipayment.data.vo.SaleVO;
import com.rafhael.barabas.apipayment.entities.Sale;
import com.rafhael.barabas.apipayment.entities.SaleProduct;
import com.rafhael.barabas.apipayment.exception.ResourceNotFoundException;
import com.rafhael.barabas.apipayment.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    
    private final SaleRepository repository;
    private final SaleProductService saleProductService;

    @Autowired
    public SaleService(final SaleRepository repository, final SaleProductService saleProductService) {
        this.repository = repository;
        this.saleProductService = saleProductService;
    }


    private SaleVO convertEntityToVO(Sale sale) {
        return SaleVO.create(sale);
    }

    private Sale convertVOToEntity(SaleVO saleVo) {
        return Sale.create(saleVo);
    }

    public SaleVO create(SaleVO saleVO) {
        Sale sale = repository.save(convertVOToEntity(saleVO));

        List<SaleProduct> saleProducts = new ArrayList<>();

        saleVO.getProducts().forEach( spVO -> {
            SaleProduct sp = SaleProduct.create(spVO);
            sp.setSale(sale);
            saleProducts.add(saleProductService.create(sp));
        });
        sale.setProducts(saleProducts);
        return convertEntityToVO(sale);
    }

    public Page<SaleVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertEntityToVO);
    }

    public SaleVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found for this ID"));
        return convertEntityToVO(entity);
    }

    public SaleVO update(SaleVO saleVO){
        findById(saleVO.getId());
        Sale save = repository.save(convertVOToEntity(saleVO));
        return convertEntityToVO(save);
    }

    public SaleVO update(Long id){
        SaleVO saleVO = findById(id);
        Sale save = repository.save(convertVOToEntity(saleVO));
        return convertEntityToVO(save);
    }

    public void delete(Long id){
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found for this ID"));
        repository.delete(entity);
    }
}
