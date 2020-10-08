package com.github.cjgd.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.cjgd.model.entity.OrderItem;

@Repository
public interface OrderItemRepo extends CrudRepository<OrderItem, Long> {
}
