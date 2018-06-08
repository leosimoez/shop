package br.com.lm.shop.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lm.shop.entities.Category;
import br.com.lm.shop.repository.CategoryRepository;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	@ResponseStatus(code=HttpStatus.OK)
	@ResponseBody
	public Category read(@PathVariable("id") Integer categoryId) {
		return repository.findById(categoryId).orElse(null);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(code=HttpStatus.OK)
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public Page<Category> read(Pageable page, Principal principal) {
		System.out.println(principal);
		return repository.findAll(page);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(code=HttpStatus.CREATED)
	@ResponseBody
	public Category create(@RequestBody Category c) {
		return repository.save(c);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseStatus(code=HttpStatus.OK)
	@ResponseBody
	public Category update(@RequestBody Category c) {
		return repository.save(c);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(Integer categoryId) {
		repository.deleteById(categoryId);
	}
	
}
