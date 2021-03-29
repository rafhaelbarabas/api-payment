package com.rafhael.barabas.apipayment.api.controllers;

import com.rafhael.barabas.apipayment.data.vo.SaleVO;
import com.rafhael.barabas.apipayment.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;
    private final PagedResourcesAssembler<SaleVO> assembler;

    @Autowired
    public SaleController(SaleService service, PagedResourcesAssembler<SaleVO> assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    private void createLink(SaleVO saleVO) {
        saleVO.add(linkTo(methodOn(SaleController.class).findById(saleVO.getId())).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<SaleVO> findById(@PathVariable("id") Long id) {
        SaleVO saleVO = service.findById(id);
        createLink(saleVO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(saleVO);
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sort = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sort, "date"));
        Page<SaleVO> products = service.findAll(pageable);
        products.forEach(this::createLink);
        PagedModel<EntityModel<SaleVO>> pagedModel = assembler.toModel(products);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagedModel);
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<SaleVO> create(@RequestBody SaleVO saleVO) {
        var sale = service.create(saleVO);
        createLink(sale);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sale);
    }
}
