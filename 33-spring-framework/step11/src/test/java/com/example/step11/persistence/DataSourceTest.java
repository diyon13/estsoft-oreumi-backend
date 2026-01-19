package com.example.step11.persistence;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

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

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testSqlSessionFactory() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession();
             Connection connection = sqlSession.getConnection()) {
            log.info("sqlSession = {}", sqlSession);
            log.info("connection = {}", connection);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
