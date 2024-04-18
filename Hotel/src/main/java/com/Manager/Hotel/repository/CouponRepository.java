package com.Manager.Hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Manager.Hotel.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Coupon findByCode(String code);

    

}
