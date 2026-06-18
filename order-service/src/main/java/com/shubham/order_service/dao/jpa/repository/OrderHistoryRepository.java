package com.shubham.order_service.dao.jpa.repository;

import com.shubham.order_service.dao.jpa.enity.OrderHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, UUID> {
    List<OrderHistoryEntity> findBYOrderId(UUID orderId);

}
