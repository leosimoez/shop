package br.com.lm.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lm.shop.entities.Product;
import br.com.lm.shop.repository.ProductRepository;

@RestController
@RequestMapping(value = "/products", produces=MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private ProductRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public Page<Product> read(@Param("searchString") String searchString, Pageable page) {
		return (searchString != null && !searchString.isEmpty())
			? repository.searchProducts(searchString, page)
			: repository.findAll(page);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Product read(@PathVariable("id") Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@RequestMapping(method = {RequestMethod.POST})
	@ResponseBody
	@ResponseStatus(code=HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return repository.save(product);
	}
	
	@RequestMapping(value="/{id}", method = {RequestMethod.PUT})
	@ResponseBody
	public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
		return repository.save(product);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long productId) {
		repository.deleteById(productId);
	}
	
	
}
