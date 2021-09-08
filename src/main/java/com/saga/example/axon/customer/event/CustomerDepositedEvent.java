package com.saga.example.axon.customer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/22.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDepositedEvent {

    private String customerId;
    private Double amount;



}
