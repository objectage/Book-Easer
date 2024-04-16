package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;


import com.Manager.Hotel.service.PublisherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PublisherController {

    @Autowired
    private PublisherService publishService;

    @GetMapping("/publisher")
    public String getPublishForm(Model model){
        return "Admin/publisher";
    }
    
    @PostMapping("/publisher")
    public String publishToCustomer(Model model, @ModelAttribute("msg") String msg){
        publishService.publishToCustomer(msg);
        return "redirect:/admin";
    }



}
