package com.Manager.Hotel.strategy;

public class FixedDiscountStrategy implements PricingStrategy {
    private int discount;  // Discount in percentage

    public FixedDiscountStrategy(int discount) {
        this.discount = discount;
    }

    @Override
    public double calculatePrice(double basePrice, int noOfDays) {
        double discountAmount = basePrice * discount / 100;
        return (basePrice - discountAmount) * noOfDays;
    }
}
