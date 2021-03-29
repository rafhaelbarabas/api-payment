package com.rafhael.barabas.apipayment.services;

import com.rafhael.barabas.apipayment.data.vo.SaleProductVO;
import com.rafhael.barabas.apipayment.entities.SaleProduct;
import com.rafhael.barabas.apipayment.exception.ResourceNotFoundException;
import com.rafhael.barabas.apipayment.repositories.SaleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SaleProductService {

    private final SaleProductRepository repository;

    @Autowired
    public SaleProductService(SaleProductRepository repository) {
        this.repository = repository;
    }


    private SaleProductVO convertEntityToVO(SaleProduct saleProduct) {
        return SaleProductVO.create(saleProduct);
    }

    private SaleProduct convertVOToEntity(SaleProductVO saleProductVo) {
        return SaleProduct.create(saleProductVo);
    }

    public SaleProductVO create(SaleProductVO saleProductVo) {
        SaleProduct save = repository.save(convertVOToEntity(saleProductVo));
        return convertEntityToVO(save);
    }

    public SaleProduct create(SaleProduct saleProduct) {
        return repository.save(saleProduct);
    }

    public Page<SaleProductVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertEntityToVO);
    }

    public SaleProductVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SaleProduct not found for this ID"));
        return convertEntityToVO(entity);
    }

    public SaleProductVO update(SaleProductVO saleProductVo) {
        findById(saleProductVo.getId());
        SaleProduct save = repository.save(convertVOToEntity(saleProductVo));
        return convertEntityToVO(save);
    }

    public SaleProductVO update(Long id) {
        SaleProductVO saleProductVo = findById(id);
        SaleProduct save = repository.save(convertVOToEntity(saleProductVo));
        return convertEntityToVO(save);
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SaleProduct not found for this ID"));
        repository.delete(entity);
    }
}
