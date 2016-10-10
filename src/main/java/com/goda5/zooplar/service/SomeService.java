package com.goda5.zooplar.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.management.RuntimeMBeanException;

@Service
public class SomeService {
    public SomeService() {
        System.out.println("here" + Thread.currentThread().getName());
        new Thread(() -> {
            Thread.currentThread().setName("new Thread Tong");
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException();
        }).start();
    }

    @PostConstruct
    public void dosth() throws InterruptedException {
        System.out.println("here1" + Thread.currentThread().getName());
        Thread.sleep(10000);
    }
}
