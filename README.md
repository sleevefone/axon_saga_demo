

## saga 测试
### 1. 创建用户
```text
curl -X POST "http://localhost:8080/customers?name=imooc1&password=123456"
curl -X GET http://localhost:8080/customers
curl -X PUT http://localhost:8080/customers/19c48f1b-338d-49d3-b011-7d11d5189560/deposit/1000
```
### 2. 创建票：
```text
curl -X POST "http://localhost:8080/tickets?name=t1"
```

### 3. 创建订单
```text
curl -X POST -d '{"customerId": "19c48f1b-338d-49d3-b011-7d11d5189560", "title": "order_1", "ticketId": "6fcf920c-e600-43a8-8467-0acfd5144f88", "amount": 100}' -H 'Content-Type: application/json' http://localhost:8080/orders
超时：
curl -X POST -d '{"customerId": "f352dae2-3f0c-4298-b01b-7d43b6e885df", "title": "order_timeout", "ticketId": "29bf1d49-4a77-46dd-8ace-2fc469116f3e", "amount": 0}' -H 'Content-Type: application/json' http://localhost:8080/orders
```

### 4. 性能测试
```text
充值
ab -n 20 -c 10 -T 'application/json' -m PUT http://localhost:8080/customers/f55dc8ce-b960-4f20-a9df-04fc72878e06/deposit/100
提现
ab -n 20 -c 10 -T 'application/json' -m PUT http://localhost:8080/customers/f55dc8ce-b960-4f20-a9df-04fc72878e06/withdraw/200

curl -X GET http://localhost:8080/orders/tickets
curl -X GET http://localhost:8080/orders/customers

ab -n 20 -c 10 -T 'application/json' -p oneUserAllTicket.txt http://localhost:8080/orders/test/oneUserAllTicket

ab -n 20 -c 10 -T 'application/json' -p allUserOneTicket.txt http://localhost:8080/orders/test/allUserOneTicket
```


### 备注 1：读写分离的概念
```text
    //查询的是物化视图，非事件 eventStore
    @GetMapping("")
    public List<CustomerEntity> getAllCustomers() {
        LOG.info("Request all Customers");
        return customerRepository.findAll();
    }

    //仅仅在测试环境中使用如下查询方式（axon 会（聚合）执行所有的 event），查询的是 eventStore
    @GetMapping("/query")
    public Customer queryOnlyInTestEvn(CustomerId customerId) throws ExecutionException, InterruptedException {
        return queryGateway.query(customerId, Customer.class).get();
    }
```