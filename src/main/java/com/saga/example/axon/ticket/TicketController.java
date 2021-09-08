package com.saga.example.axon.ticket;

import com.saga.example.axon.ticket.command.TicketCreateCommand;
import com.saga.example.axon.ticket.query.TicketEntity;
import com.saga.example.axon.ticket.query.TicketEntityRepository;
import com.saga.example.axon.utils.IdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by mavlarn on 2018/5/28.
 */
@RestController
@RequestMapping("/tickets")
@Api(tags = "2: ticketController")
public class TicketController {

    private static final Logger LOG = LoggerFactory.getLogger(TicketController.class);
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private TicketEntityRepository ticketEntityRepository;

    @ApiOperation("create")
    @PostMapping("")
    public CompletableFuture<Object> create(@RequestParam String name) {
        LOG.info("Request to create ticket:{}", name);
        TicketCreateCommand command = new TicketCreateCommand(IdUtils.getUuid(), name);
        return commandGateway.send(command);
    }


    @GetMapping("")
    @ApiOperation("all")
    public List<TicketEntity> all() {
        return ticketEntityRepository.findAll();
    }
}
