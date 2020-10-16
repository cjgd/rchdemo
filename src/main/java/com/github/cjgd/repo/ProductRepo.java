package com.github.cjgd.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.cjgd.model.entity.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {

    Iterable<Product> findAllByEnabled(boolean b);
}
