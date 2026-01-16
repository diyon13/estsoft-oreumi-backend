package com.example.step11.ex01;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class HotelTest {
    @Autowired
    private Hotel hotel;

    @Test
    public void testExists() {
        assertNotNull(hotel);

        log.info("hotel = {}", hotel);
        log.info("hotel.chef = {}", hotel.getChef());
    }
}