package com.lingfenglong.springcloud;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class TimeTest {
    @Test
    void GetTime() {
        System.out.println("ZonedDateTime.now() = " + ZonedDateTime.now());
    }
}
