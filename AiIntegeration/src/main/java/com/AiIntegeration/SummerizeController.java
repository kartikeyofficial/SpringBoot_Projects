package com.AiIntegeration;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
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
