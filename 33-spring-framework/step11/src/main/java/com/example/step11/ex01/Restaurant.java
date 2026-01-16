package com.example.step11.ex01;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// @Component 주해로 Spring 컨테이너에게 이 클래스가 빈(bean)으로 관리해야 하는 대상임을 알린다.
@Component

// Lombok 라이브러리의  @Data 주해로 생성자, 설정자, 접근자, toString 메서드 등을 추가한다.
@Data
class Restaurant {
    // @Autowired 주해로 Spring 컨테이너에게 이 필드에 의존 객체(빈)을 주입해달라고 명시한다.
    @Autowired
    private Chef chef;
}
