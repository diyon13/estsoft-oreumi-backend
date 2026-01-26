package com.example.step13.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
public class DataSourceTest {
    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        // try-with-resource 구문
        try (Connection connection = dataSource.getConnection()) {
            log.info("connection = {}", connection);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}