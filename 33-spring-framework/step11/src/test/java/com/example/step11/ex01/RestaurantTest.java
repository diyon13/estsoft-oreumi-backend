package com.example.step11.ex01;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RestaurantTest {
    @Autowired
    private Restaurant restaurant;

    @Test

    public void testExists() {
        // assertNotNull 메서드로 Restaurant 인스턴스가 null이 아님을 검증한다.
        assertNotNull(restaurant);

        // Logger 인스턴스의 출력 메서드들은 첫 번째 인자로 로그에 출력할 문자열을 지정하며,
        // 그 문자열에 "{}"가 있으면 다음에 나오는 인자들로 치환한다.
        log.info("restaurant = {}", restaurant);
        log.info("----------------------------------------");
        log.info("chef = {}", restaurant.getChef());
    }
}