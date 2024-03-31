package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

    List<Customer> findByEmail(String email);

}
