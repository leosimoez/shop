package br.com.lm.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.lm.shop.entities.Product;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

	@Query("select p from Product p where p.name like %?1% or p.fullDescription like %?1% or p.shortDescription like %?1%")
	Page<Product> searchProducts(String search, Pageable page);

	@Query("select p from Product p join p.categories c where c.id = ?1 and (p.name like %?2% or p.fullDescription like %?2% or p.shortDescription like %?2%)")
	Page<Product> searchProductsByCategory(Long categoryId, String searchString, Pageable page);

	@Query("select p from Product p join p.categories c where c.id = ?1")
	Page<Product> findProductsByCategory(Integer categoryId, Pageable page);
	
	
}