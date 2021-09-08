package com.imooc.example.axon.customer;

import com.imooc.example.axon.customer.command.CustomerChargeCommand;
import com.imooc.example.axon.customer.command.CustomerCreateCommand;
import com.imooc.example.axon.customer.command.CustomerDepositCommand;
import com.imooc.example.axon.customer.query.CustomerEntity;
import com.imooc.example.axon.customer.query.CustomerEntityRepository;
import com.imooc.example.axon.customer.query.CustomerId;
import com.imooc.example.axon.utils.IdUtils;
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
        CustomerCreateCommand createCustomerCommand = new CustomerCreateCommand(IdUtils.getUuid(), name, password);
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

    /**
     * 查询的是物化视图，非事件 eventStore
     */
    @GetMapping("")
    @ApiOperation("getAllCustomers")
    public List<CustomerEntity> getAllCustomers() {
        LOG.info("Request all Customers");
        return customerRepository.findAll();
    }

    /**
     * 仅仅在测试环境中使用如下查询方式（axon 会（聚合）执行所有的 event），查询的是 eventStore
     */
    @GetMapping("/query")
    @ApiOperation("queryOnlyInTestEvn")
    public Customer queryOnlyInTestEvn(CustomerId customerId) throws ExecutionException, InterruptedException {
        return queryGateway.query(customerId, Customer.class).get();
    }
}
