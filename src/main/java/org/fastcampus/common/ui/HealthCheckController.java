package org.fastcampus.common.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 🌱 Spring을 세팅하고, 정상적으로 응답이 오는지 확인하기 위하여 만든 Controller 🌱
@RestController
public class HealthCheckController {

    @GetMapping
    public String healthCheck() {
        return "OK";
    }

}
