package br.com.lm.shop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.lm.shop.entities.Category;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {


}