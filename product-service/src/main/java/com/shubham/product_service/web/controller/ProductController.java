package com.shubham.product_service.web.controller;

import com.shubham.core.dto.Product;
import com.shubham.product_service.dto.ProductCreationRequest;
import com.shubham.product_service.dto.ProductCreationResponse;
import com.shubham.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCreationResponse save(@RequestBody @Valid ProductCreationRequest request){
        var product = new Product();
        BeanUtils.copyProperties(request,product);
        Product result = productService.save(product);

        var productCreationResponse = new ProductCreationResponse();
        BeanUtils.copyProperties(result,productCreationResponse);
        return productCreationResponse;

    }

}
