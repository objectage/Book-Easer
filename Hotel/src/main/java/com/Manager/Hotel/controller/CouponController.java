package com.Manager.Hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;



import com.Manager.Hotel.entity.Coupon;
import com.Manager.Hotel.service.CouponService;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping("/coupon")
    public String coupon(Model model) {
        model.addAttribute("coupon", couponService.getAllCoupons());
        return "Admin/coupon";
    }

    @GetMapping("/addCoupon")   
    public String addCoupon(Model model) {
        model.addAttribute("coupon", new Coupon());
        return "Admin/addCoupon";
    }

    @RequestMapping(value = "/addCoupon", method = RequestMethod.POST)
    public String addCoupon(@ModelAttribute Coupon coupon) {
        couponService.createCoupon(coupon);
        return "redirect:/coupon";
    }

    @PostMapping("/deleteCoupon/{id}")
    public String deleteCoupon(@PathVariable("id") Long id) {
        couponService.deleteCoupon(id);
        return "redirect:/coupon";
    }

    
}
