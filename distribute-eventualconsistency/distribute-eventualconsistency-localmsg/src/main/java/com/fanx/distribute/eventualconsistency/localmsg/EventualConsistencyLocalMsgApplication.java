package com.fanx.distribute.eventualconsistency.localmsg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventualConsistencyLocalMsgApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventualConsistencyLocalMsgApplication.class, args);
    }
}
