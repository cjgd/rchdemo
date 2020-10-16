package com.github.cjgd.RchMain;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.cjgd.model.entity.Order;
import com.github.cjgd.model.entity.OrderItem;
import com.github.cjgd.model.entity.Product;
import com.github.cjgd.model.rest.OrderR;
import com.github.cjgd.model.rest.ProductR;
import com.github.cjgd.repo.OrderItemRepo;
import com.github.cjgd.repo.OrderRepo;
import com.github.cjgd.repo.ProductRepo;

@Service
public class RchService {

    private static final Logger LOG = LoggerFactory.getLogger(RchService.class);

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    /*
     * PRODUCT support
     */

    public long productCreate(ProductR product) {
        Product p = new Product(product.getName(), product.getPrice());
        Product saved = productRepo.save(p);
        LOG.debug("created product: {}, from: {}", saved, product);
        return saved.getId();
    }

    public Iterable<Product> productList() {
        return productRepo.findAllByEnabled(true);
    }

    public boolean productUpdate(long productId, ProductR product) {
        Optional<Product> op = productRepo.findById(productId);
        if (op.isPresent()) {
            Product p = op.get();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            productRepo.save(p);
            LOG.debug("updated product: {}", p);
            return true;
        }
        LOG.info("failed to update missing product: {}, with: {}", productId, product);
        return false;
    }

    public boolean productDisable(long productId) {
        Optional<Product> op = productRepo.findById(productId);
        if (op.isPresent()) {
            Product p = op.get();
            p.setEnabled(false);
            productRepo.save(p);
            LOG.debug("disabled product: {}", p);
            return true;
        }
        LOG.info("failed to delete missing product: {}", productId);
        return false;
    }

    /*
     * ORDER support
     */

    @Transactional
    public long orderCreate(OrderR order) {
        String email = order.getEmail();
        Order o = new Order(email);
        Order savedOrder = orderRepo.save(o);

        if (order.getProductIds() != null) {
            Iterable<Product> products = productRepo.findAllById(order.getProductIds());
            for (Product p : products) {
                if (!p.isEnabled())
                    continue;
                OrderItem orderItem = new OrderItem(savedOrder, p);
                orderItemRepo.save(orderItem);
            }
        }
        LOG.debug("created order: {}", savedOrder);
        return savedOrder.getId();
    }

    public Iterable<Order> orderList(Date startDate, Date endDate) {
        if (startDate == null && endDate == null)
            return orderRepo.findAll();
        if (startDate != null && endDate != null)
            return orderRepo.findAllByCreatedOnGreaterThanEqualAndCreatedOnLessThanEqual(startDate, endDate);
        if (startDate != null)
            return orderRepo.findAllByCreatedOnGreaterThanEqual(startDate);
        return orderRepo.findAllByCreatedOnLessThanEqual(endDate);
    }

}
