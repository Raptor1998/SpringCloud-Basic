package com.raptor;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author raptor
 * @description Test
 * @date 2021/8/30 20:26
 */
@Slf4j
public class Test {
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    @org.junit.Test
    public void context() {
        for (int i = 0; i < 5; i++) {
            System.out.println(this.incrementAndGetModulo(3));
        }
    }

    private int incrementAndGetModulo(int modulo) {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = (current + 1) % modulo;
            log.info("current:{},next:{}", current, next);
        } while (!this.nextServerCyclicCounter.compareAndSet(current, next));
        log.info("AtomicInteger:{}",this.nextServerCyclicCounter.get());
        return next;
    }
}
