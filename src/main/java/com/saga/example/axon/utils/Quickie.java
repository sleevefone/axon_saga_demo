package com.saga.example.axon.utils;

import org.slf4j.*;

public class Quickie {
    static final Logger LOG = LoggerFactory.getLogger(Quickie.class);

    public static void main(String[] args) throws Exception {

        MyThread t1 = new MyThread("hi");
        MyThread t2 = new MyThread("bye");

        t1.start();
        t2.start();
    }
}

class MyThread extends Thread {
    static final Logger LOG = LoggerFactory.getLogger(MyThread.class);

    public MyThread(final String name) {
        this.setName(name);
    }

    public void run() {
        logSomething();
    }

    public void logSomething() {
        MDC.put("mdc_key", this.getName());
        LOG.info(getName());
        LOG.error(getName());
    }
}

