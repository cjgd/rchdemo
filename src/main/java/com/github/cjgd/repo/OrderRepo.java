package com.github.cjgd.repo;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.cjgd.model.entity.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

    Iterable<Order> findAllByCreatedOnGreaterThanEqualAndCreatedOnLessThanEqual(Date startDate, Date endDate);

    Iterable<Order> findAllByCreatedOnGreaterThanEqual(Date startDate);

    Iterable<Order> findAllByCreatedOnLessThanEqual(Date endDate);
}
