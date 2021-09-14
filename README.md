

## 测试(api test)
```text
swagger: http://localhost:8080/doc.html
```

### 1. 创建用户(create customer)
```text
curl -X POST "http://localhost:8080/customers?name=imooc1&password=123456"
curl -X GET http://localhost:8080/customers
curl -X PUT http://localhost:8080/customers/19c48f1b-338d-49d3-b011-7d11d5189560/deposit/1000
```
### 2. 创建票(create tickets)
```text
curl -X POST "http://localhost:8080/tickets?name=t1"
```

### 3. 创建订单(create order)
```text
curl -X POST -d '{"customerId": "943638870e664682b949e9a2a5f23a1f", "title": "order_1", "ticketId": "82bcbcfe8eac47beba2b89ece30fd096", "amount": 100}' -H 'Content-Type: application/json' http://localhost:8080/orders
超时：
curl -X POST -d '{"customerId": "f352dae2-3f0c-4298-b01b-7d43b6e885df", "title": "order_timeout", "ticketId": "29bf1d49-4a77-46dd-8ace-2fc469116f3e", "amount": 0}' -H 'Content-Type: application/json' http://localhost:8080/orders
```

### 4. replay events

```text
curl -X GET "http://localhost:8080/replay" -H "accept: */*"

```


### 5. 性能测试(performance test)
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

 