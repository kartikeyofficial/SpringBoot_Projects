package com.AiIntegeration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SummerizeController {

    private SummerizeService summerizeService;

    public SummerizeController(SummerizeService summerizeService) {
        this.summerizeService = summerizeService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String message){
        return summerizeService.chat(message);
    }
}
