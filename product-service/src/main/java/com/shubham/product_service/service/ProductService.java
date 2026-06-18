package com.shubham.product_service.service;

import com.shubham.core.dto.Product;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;
import java.util.UUID;

public interface ProductService
{
    List<Product> findAll();
    Product reserve(Product desiredProduct,UUID orderID);
    void cancelReservation(Product producttoCancel, UUID orderId);
    Product save(Product product);

}
