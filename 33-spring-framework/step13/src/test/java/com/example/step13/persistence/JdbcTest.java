package com.example.step13.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
public class JdbcTest {
    // 정적 초기화 블록(static initialization block)
    static {
        try {
            // JDBC 드라이버 로드
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @Test
    public void testConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            log.info("connection = {}", connection);
        } catch (Exception e) {
            fail(e.getMessage());
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception ignored) {}
        }
    }
}