package com.arianit.tripbooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/oauth")
    public String getMsg(){
        return "Home with oauth2";
    }
}
