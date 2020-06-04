package io.korconut.reservationpractice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.korconut.reservationpractice.dto.Product;
import io.korconut.reservationpractice.service.ProductService;

@RestController
@RequestMapping(path="/products")
public class ProductApiController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public Map<String, Object> items(@RequestParam(name = "categoryId", required = true) int categoryId, 
			@RequestParam(name="start", required=false, defaultValue="0") int start) {
		
		List<Product> items = productService.getProducts(categoryId, start);
		
		int totalCount = productService.getCount(categoryId);

		Map<String, Object> map = new HashMap<>();
		
		map.put("items", items);
		map.put("totalCount", totalCount);
		
		return map;
	}
}