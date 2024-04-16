package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.Manager.Hotel.repository.CustomerRepository;
import com.Manager.Hotel.entity.Customer;



@Service
public class PublisherService {

    @Autowired
    private CustomerRepository customerRepository;

    public void publishToCustomer(String msg){
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            if (customer.isSubscribed()) {
                System.out.println("Sending email to: " + customer.getEmail());
            }
        }
    }


}
