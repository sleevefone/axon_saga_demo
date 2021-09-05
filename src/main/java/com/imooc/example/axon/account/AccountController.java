package com.imooc.example.axon.account;

import com.imooc.example.axon.account.command.AccountCreateCommand;
import com.imooc.example.axon.account.command.AccountDepositCommand;
import com.imooc.example.axon.account.command.AccountWithdrawCommand;
import com.imooc.example.axon.account.query.AccountEntity;
import com.imooc.example.axon.account.query.AccountEntityRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by mavlarn on 2018/5/22.
 */
@RestController
@RequestMapping("/accounts")
@Api(tags = "acc")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private AccountEntityRepository accountEntityRepository;

    @PostMapping("")
    @ApiOperation("create")
    public CompletableFuture<Object> createBankAccount(@RequestParam String name) {
        log.info("Request to create account for: {}", name);
        UUID accountId = UUID.randomUUID();
        AccountCreateCommand createAccountCommand = new AccountCreateCommand(accountId.toString(), name);
        return commandGateway.send(createAccountCommand);
    }

    @PutMapping("/{accountId}/deposit/{amount}")
    @ApiOperation("depositMoney")
    public CompletableFuture<Object> depositMoney(@PathVariable String accountId, @PathVariable Double amount) {
        log.info("Request to depositMoney {} from account {} ", amount, accountId);
        return commandGateway.send(new AccountDepositCommand(accountId, amount));
    }

    @PutMapping("/{accountId}/withdraw/{amount}")
    @ApiOperation("withdrawMoney")
    public CompletableFuture<Object> withdrawMoney(@PathVariable String accountId, @PathVariable Double amount) {
        log.info("Request to withdraw {} from account {} ", amount, accountId);
        return commandGateway.send(new AccountWithdrawCommand(accountId, amount));
    }

    @GetMapping("/{accountId}")
    @ApiOperation("getAccountById")
    public AccountEntity getAccountById(@PathVariable String accountId) {
        log.info("Request Account with id: {}", accountId);
        AccountEntity acc = new AccountEntity();
        acc.setId(accountId);
        Example<AccountEntity> ex = Example.of(acc);

        Optional<AccountEntity> one = accountEntityRepository.findOne(ex);
        return one.orElse(null);
    }

    @GetMapping("")
    @ApiOperation("getAllAccounts")
    public List<AccountEntity> getAllAccounts() {
        log.info("Request all Accounts");
        return accountEntityRepository.findAll();
    }
}
