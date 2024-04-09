package com.Manager.Hotel.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @Temporal(javax.persistence.TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    private int noOfDays;
    private double totalPrice;
    private boolean paymentStatus;
    private boolean checkInStatus;
    private boolean checkOutStatus;

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }   

    public Boolean getCheckInStatus() {
        return checkInStatus;
    }

    public Boolean getCheckOutStatus() {
        return checkOutStatus;
    }

    public Long getRoomID() {
        return room.getId();
    }

    public Long getCustomerID() {
        return customer.getId();
    }
    
}
