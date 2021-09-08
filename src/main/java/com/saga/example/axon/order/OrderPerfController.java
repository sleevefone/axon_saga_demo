package com.saga.example.axon.order;

import com.saga.example.axon.customer.query.CustomerEntity;
import com.saga.example.axon.customer.query.CustomerEntityRepository;
import com.saga.example.axon.order.command.OrderCreateCommand;
import com.saga.example.axon.ticket.query.TicketEntity;
import com.saga.example.axon.ticket.query.TicketEntityRepository;
import com.saga.example.axon.utils.IdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by mavlarn on 2018/5/28.
 */
@RestController
@RequestMapping("/orders")
@Api(tags = "3: OrderPerfController")
public class OrderPerfController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderPerfController.class);

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private TicketEntityRepository ticketEntityRepository;
    @Autowired
    private CustomerEntityRepository customerEntityRepository;

    private List<TicketEntity> ticketList;
    private List<CustomerEntity> customerList;

    @PostMapping("/test/oneUserAllTicket")
    @ApiOperation("oneUserAllTicket")
    public void oneUserAllTicket(@RequestBody Order order) {
        Random random = new Random();
        TicketEntity buyTicket = this.ticketList.get(random.nextInt(ticketList.size()));
        OrderCreateCommand command = new OrderCreateCommand(IdUtils.getUuid(), order.getCustomerId(),
                order.getTitle(), buyTicket.getId(), order.getAmount());
        LOG.info("OneUserAllTicket Create Order:{}", command);
        commandGateway.send(command, LoggingCallback.INSTANCE);
    }

    @PostMapping("/test/allUserOneTicket")
    @ApiOperation("createOrder")
    public void create(@RequestBody Order order) {
        Random random = new Random();
        CustomerEntity customer = this.customerList.get(random.nextInt(customerList.size()));
        OrderCreateCommand command = new OrderCreateCommand(IdUtils.getUuid(), customer.getId(),
                order.getTitle(), order.getTicketId(), order.getAmount());
        LOG.info("AllUserOneTicket Create Order:{}", command);
        commandGateway.send(command, LoggingCallback.INSTANCE);
    }

    @GetMapping("/tickets")
    @ApiOperation("getAllTickets")
    public List<TicketEntity> getAllTickets() {
        List<TicketEntity> tickets = ticketEntityRepository.findAll();
        this.ticketList = tickets.stream().filter(ticket -> ticket.getLockUser() == null && ticket.getOwner() == null).collect(Collectors.toList());
        return this.ticketList;
    }
    @ApiOperation("getAllCustomers")
    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers() {
        this.customerList = customerEntityRepository.findAll();
        return this.customerList;
    }
}
