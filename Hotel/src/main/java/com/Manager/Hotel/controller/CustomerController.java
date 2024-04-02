package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;


import com.Manager.Hotel.entity.Customer;
import com.Manager.Hotel.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer")
    public String customer(Model model) {
        model.addAttribute("customer", customerService.getAllCustomers());
        return "Customer/customer";
    }

    @RequestMapping(value="/customer/name", method = RequestMethod.GET)
    public String showCustomerByNameForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/customer_by_name";
    }

    @RequestMapping(value="/customer/name", method = RequestMethod.POST)
    public String getCustomerByName(Model model, @ModelAttribute("customer") Customer customer) {
        model.addAttribute("customer", customerService.getCustomersByName(customer.getName()));
        return "Customer/customer";
    }

    @RequestMapping(value="/customer/new", method = RequestMethod.GET)
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/new_customer";
    }

    @RequestMapping(value="/customer/new", method = RequestMethod.POST)
    public String newCustomer(Model model, @ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer";
    }

    @RequestMapping(value="/customer/edit", method = RequestMethod.GET)
    public String showEditCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/edit_customer";
    }

    @RequestMapping(value="/customer/edit", method = RequestMethod.POST)
    public String editCustomer(Model model, @ModelAttribute("customer") Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customer";
    }

    @RequestMapping(value="/customer/delete", method = RequestMethod.GET)
    public String showDeleteCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer/delete_customer";
    }

    @RequestMapping(value="/customer/delete", method = RequestMethod.POST)
    public String deleteCustomer(Model model, @ModelAttribute("customer") Customer customer) {
        customerService.deleteCustomer(customer.getId());
        return "redirect:/customer";
    }

}
