package com.goda5.zooplar.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SomeService {
    public SomeService() {
        System.out.println("here" + Thread.currentThread().getName());
    }

    @PostConstruct
    public void dosth() {
        System.out.println("here1" + Thread.currentThread().getName());
    }
}
