package com.saga.example.axon.customer.query;

import com.saga.example.axon.customer.event.CustomerChargedEvent;
import com.saga.example.axon.customer.event.CustomerCreatedEvent;
import com.saga.example.axon.customer.event.CustomerDepositedEvent;
import com.saga.example.axon.customer.event.OrderPaidEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by mavlarn on 2018/5/22.
 */
@Service
@ProcessingGroup("customerProjector")
//@AllowReplay
public class CustomerProjector {

    @Autowired
    private CustomerEntityRepository jpaCustomerEntityRepository;

//    @Resource
//    private org.axonframework.modelling.command.Repository<CustomerEntity> axonRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        CustomerEntity customer = new CustomerEntity(event.getCustomerId(), event.getName(), event.getPassword(), 0d);
        jpaCustomerEntityRepository.save(customer);
    }

    @EventHandler
    public void on(CustomerDepositedEvent event) {
        String customerId = event.getCustomerId();
        CustomerEntity accountView = jpaCustomerEntityRepository.getOne(customerId);

        Double newDeposit = accountView.getDeposit() + event.getAmount();
        accountView.setDeposit(newDeposit);
        jpaCustomerEntityRepository.save(accountView);
    }

    @EventHandler
    public void on(CustomerChargedEvent event) {

//        Aggregate<CustomerEntity> one = axonRepository.load(event.getCustomerId());
//        one.execute(o -> {
//            Double newDeposit = o.getDeposit() - event.getAmount();
//            o.setDeposit(newDeposit);
//            jpaCustomerEntityRepository.save(o);
//
//        });

        Example<CustomerEntity> ex = Example.of(CustomerEntity.builder()
                .id(event.getCustomerId())
                .build());
        jpaCustomerEntityRepository.findOne(ex)
                .ifPresent(o -> {
                    Double newDeposit = o.getDeposit() - event.getAmount();
                    o.setDeposit(newDeposit);
                    jpaCustomerEntityRepository.save(o);

                });

    }

    @EventHandler
    public void on(OrderPaidEvent event) {
        String customerId = event.getCustomerId();
        CustomerEntity customer = jpaCustomerEntityRepository.getOne(customerId);

        Double newDeposit = customer.getDeposit() - event.getAmount();
        customer.setDeposit(newDeposit);
        jpaCustomerEntityRepository.save(customer);
    }
}
