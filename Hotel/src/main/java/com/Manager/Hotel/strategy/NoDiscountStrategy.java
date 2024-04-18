package com.Manager.Hotel.strategy;

public class NoDiscountStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice, int noOfDays) {
        return basePrice * noOfDays;
    }
}
