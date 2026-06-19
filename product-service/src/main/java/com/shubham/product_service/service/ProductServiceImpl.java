package com.shubham.product_service.service;

import com.shubham.core.dto.Product;
import com.shubham.core.exception.ProductInsufficientQuantityException;
import com.shubham.product_service.dao.jpa.entity.ProductEntity;
import com.shubham.product_service.dao.jpa.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(entity->new Product(entity.getId(),entity.getName(),entity.getPrice(),entity.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public Product reserve(Product desiredProduct, UUID orderID) {
        ProductEntity productEntity = productRepository.findById(desiredProduct.getId()).orElseThrow();
        if(desiredProduct.getQunaltity() > productEntity.getQuantity()){
            throw new ProductInsufficientQuantityException(productEntity.getId(),orderID);
        }
        productEntity.setQuantity(productEntity.getQuantity()-desiredProduct.getQunaltity());
        productRepository.save(productEntity);
        var reservedProduct = new Product();
        BeanUtils.copyProperties(productEntity,reservedProduct);
        reservedProduct.setQunaltity(desiredProduct.getQunaltity());
        return reservedProduct;
    }

    @Override
    public void cancelReservation(Product producttoCancel, UUID orderId) {
        ProductEntity productEntity = productRepository.findById(producttoCancel.getId()).orElseThrow();
        productEntity.setQuantity(productEntity.getQuantity() + producttoCancel.getQunaltity());
        productRepository.save(productEntity);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setQuantity(product.getQunaltity());
        productRepository.save(productEntity);
        return new Product(productEntity.getId(),product.getName(),product.getPrice(),product.getQunaltity());
    }

}
