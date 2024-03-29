package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
