package com.example.step11.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@Slf4j
class TimeMapperTest {
    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void testGetTime() {
        String time = timeMapper.getTime();
        assertNotNull(time);

        log.info("className = {}", timeMapper.getClass().getName());
        log.info("time = {}", time);
    }

    @Test
    public void testGetTime2() {
        String time = timeMapper.getTime();
        assertNotNull(time);

        log.info("className = {}", timeMapper.getClass().getName());
        log.info("time = {}", time);
    }
}