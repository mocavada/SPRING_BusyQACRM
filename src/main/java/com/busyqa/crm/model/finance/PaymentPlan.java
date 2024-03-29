package com.busyqa.crm.model.finance;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "PAYMENT_PLANS")
public class PaymentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentPlanId;

    private String name;
    private String paymentMethod;
    private int weekFrequency;

    // DATE
    @CreationTimestamp
    private LocalDateTime createdTime;
    @UpdateTimestamp
    private LocalDateTime modifiedTime;

    public PaymentPlan() {
    }


    public PaymentPlan(String name, String paymentMethod, int weekFrequency, LocalDateTime createdTime, LocalDateTime modifiedTime) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.weekFrequency = weekFrequency;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Long paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeekFrequency() {
        return weekFrequency;
    }

    public void setWeekFrequency(int weekFrequency) {
        this.weekFrequency = weekFrequency;

    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
