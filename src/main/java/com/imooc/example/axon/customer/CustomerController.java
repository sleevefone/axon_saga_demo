package com.imooc.example.axon.customer;

import com.imooc.example.axon.customer.command.CustomerChargeCommand;
import com.imooc.example.axon.customer.command.CustomerCreateCommand;
import com.imooc.example.axon.customer.command.CustomerDepositCommand;
import com.imooc.example.axon.customer.query.CustomerEntity;
import com.imooc.example.axon.customer.query.CustomerEntityRepository;
import com.imooc.example.axon.customer.query.CustomerId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by mavlarn on 2018/5/22.
 */
@RestController
@RequestMapping("/customers")
@Api(tags = "4: CustomerController")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Resource
    private CommandGateway commandGateway;
    @Resource
    private QueryGateway queryGateway;
    @Resource
    private CustomerEntityRepository customerRepository;

    @PostMapping("")
    @ApiOperation("createCustomer")
    public CompletableFuture<Object> create(@RequestParam String name, @RequestParam String password) {
        LOG.info("Request to create account for: {}", name);
        UUID accountId = UUID.randomUUID();
        CustomerCreateCommand createCustomerCommand = new CustomerCreateCommand(accountId.toString(), name, password);
        return commandGateway.send(createCustomerCommand);
    }

    @PutMapping("/{accountId}/deposit/{amount}")
    @ApiOperation("depositMoney")
    public CompletableFuture<Object> depositMoney(@PathVariable String accountId, @PathVariable Double amount) {
        LOG.info("Request to withdraw {} from account {} ", amount, accountId);
        return commandGateway.send(new CustomerDepositCommand(accountId, amount));
    }

    @PutMapping("/{accountId}/withdraw/{amount}")
    @ApiOperation("withdrawMoney")
    public CompletableFuture<Object> withdrawMoney(@PathVariable String accountId, @PathVariable Double amount) {
        LOG.info("Request to withdraw {} from account {} ", amount, accountId);
        return commandGateway.send(new CustomerChargeCommand(accountId, amount));
    }


    @GetMapping("/{accountId}")
    @ApiOperation("getCustomerById")
    public CustomerEntity getCustomerById(@PathVariable String accountId) {
        LOG.info("Request Customer with id: {}", accountId);
        return customerRepository.findOne(accountId);
    }

    @GetMapping("")
    @ApiOperation("getAllCustomers")
    public List<CustomerEntity> getAllCustomers() {
        LOG.info("Request all Customers");
        return customerRepository.findAll();
    }

    @GetMapping("/query/{customerId}")
    @ApiOperation("getAggregate_")
    public Customer getAggregate_(CustomerId customerId) throws ExecutionException, InterruptedException {
        return queryGateway.query(customerId, Customer.class).get();
    }
}
