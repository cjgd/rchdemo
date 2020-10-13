package com.github.cjgd.RchMain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.StreamUtils;
import org.springframework.test.context.ActiveProfiles;

import com.github.cjgd.model.entity.Product;
import com.github.cjgd.model.rest.ProductR;

@SpringBootTest
@ActiveProfiles("test")
class RchMainApplicationTests {

	@Autowired
	private RchService rch;

	@Test
	void testProduct() throws Exception {
		// create / list
		ProductR p = new ProductR();
		p.setName("p1");
		p.setPrice(1);
		rch.productCreate(p);
		List<Product> ps = toList(rch.productList());
		assertEquals(1, ps.size());
		assertEquals(p.getName(), ps.get(0).getName());
		assertEquals(p.getPrice(), ps.get(0).getPrice());
		assertNotNull(ps.get(0).getId());
		assertNotNull(ps.get(0).getCreatedOn());

		// update / list
		p.setPrice(2);
		rch.productUpdate(ps.get(0).getId(), p);
		ps = toList(rch.productList());
		assertEquals(1, ps.size());
		assertEquals(p.getName(), ps.get(0).getName());
		assertEquals(p.getPrice(), ps.get(0).getPrice());

		// disable / list
		rch.productDisable(ps.get(0).getId());
		ps = toList(rch.productList());
		assertTrue(ps.isEmpty());
	}


	static <T> List<T> toList(Iterable<T> it) {
		return StreamUtils.createStreamFromIterator(it.iterator()).collect(Collectors.toList());
	}
}
