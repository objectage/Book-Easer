package com.Manager.Hotel.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.Manager.Hotel.repository.CouponRepository;
import com.Manager.Hotel.entity.Coupon;
import com.Manager.Hotel.strategy.PricingStrategy;
import com.Manager.Hotel.strategy.NoDiscountStrategy;
import com.Manager.Hotel.strategy.FixedDiscountStrategy;


@Service
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;
    
    
    public PricingStrategy getPricingStrategy(String couponCode) {
        Coupon coupon = couponRepository.findByCode(couponCode);
        if (coupon != null) {
            return new FixedDiscountStrategy(coupon.getDiscount());
        }
        return new NoDiscountStrategy();
    }

    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code);
    }

    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Iterable<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public void deleteCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id).orElse(null);
        couponRepository.delete(coupon);
    }
}



