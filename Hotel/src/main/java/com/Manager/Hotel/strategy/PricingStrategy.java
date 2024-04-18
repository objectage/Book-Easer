package com.Manager.Hotel.strategy;

public interface PricingStrategy {
    double calculatePrice(double basePrice, int noOfDays);
}
