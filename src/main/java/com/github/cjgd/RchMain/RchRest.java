package com.github.cjgd.RchMain;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.cjgd.model.entity.Order;
import com.github.cjgd.model.entity.Product;
import com.github.cjgd.model.rest.OrderR;
import com.github.cjgd.model.rest.ProductR;

@RestController
@RequestMapping(value = "rest")
public class RchRest {

    private static final Logger LOG = LoggerFactory.getLogger(RchRest.class);

	@Autowired
	private RchService rch;

	/*
	 * PRODUCT
	 */

	/** create a new product */
    @RequestMapping(value = "product", method = RequestMethod.POST)
	public Map<String, Long> productCreate(@RequestBody ProductR product) {
    	long id = rch.productCreate(product);
		return Collections.singletonMap("id", id);
	}

    /** retrieve all products */
    @RequestMapping(value = "products", method = RequestMethod.GET)
	public Iterable<Product> productList() {
    	return rch.productList();
	}

    /** update product */
    @RequestMapping(value = "product/{id}", method = RequestMethod.PUT)
	public Map<String, Boolean> productUpdate(@PathVariable("id") long productId, @RequestBody ProductR product) {
    	boolean status = rch.productUpdate(productId, product);
    	return Collections.singletonMap("status", status);
    }

	/** delete a product */
    @RequestMapping(value = "product/{id}", method = RequestMethod.DELETE)
	public Map<String, Boolean> product(@PathVariable("id") long productId) {
    	boolean status = rch.productDisable(productId);
    	return Collections.singletonMap("status", status);
	}

    /*
     * ORDER
     */

    /** place an order */
    @RequestMapping(value = "order", method = RequestMethod.POST)
	public Map<String, Long> orderCreate(@RequestBody OrderR order) {
    	long id = rch.orderCreate(order);
		return Collections.singletonMap("id", id);
	}

    /** return all orders within a time period */
    @RequestMapping(value = "orders", method = RequestMethod.GET)
	public Iterable<Order> orderList(
			@PathParam("start") @DateTimeFormat(iso = ISO.DATE) Date startDate,
			@PathParam("end") @DateTimeFormat(iso = ISO.DATE) Date endDate) {
    	LOG.debug("requesting order list between: {}, and: {}", startDate, endDate);
    	return rch.orderList(startDate, endDate);
	}

}


