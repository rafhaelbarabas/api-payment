package com.rafhael.barabas.apipayment.services;

import com.rafhael.barabas.apipayment.data.vo.ProductVO;
import com.rafhael.barabas.apipayment.entities.Product;
import com.rafhael.barabas.apipayment.exception.ResourceNotFoundException;
import com.rafhael.barabas.apipayment.repositories.ProductRepository;
import com.rafhael.barabas.apipayment.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    private ProductVO convertEntityToVO(Product product) {
        return ProductVO.create(product);
    }

    private Product convertVOToEntity(ProductVO productVo) {
        return Product.create(productVo);
    }

    public ProductVO create(ProductVO ProductVO) {
        Product save = repository.save(convertVOToEntity(ProductVO));
        return convertEntityToVO(save);
    }

    public Page<ProductVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertEntityToVO);
    }

    public ProductVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this ID"));
        return convertEntityToVO(entity);
    }

    public ProductVO update(ProductVO ProductVO){
        findById(ProductVO.getId());
        Product save = repository.save(convertVOToEntity(ProductVO));
        return convertEntityToVO(save);
    }

    public ProductVO update(Long id){
        ProductVO ProductVO = findById(id);
        Product save = repository.save(convertVOToEntity(ProductVO));
        return convertEntityToVO(save);
    }

    public void delete(Long id){
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this ID"));
        repository.delete(entity);
    }
}
