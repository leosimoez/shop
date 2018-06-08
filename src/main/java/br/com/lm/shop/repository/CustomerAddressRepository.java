package br.com.lm.shop.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.lm.shop.entities.CustomerAddress;

@Repository
public interface CustomerAddressRepository extends PagingAndSortingRepository<CustomerAddress, Long> {

}
