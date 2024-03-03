package com.estudo.azureservicebus.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class PingController {

    @RequestMapping(method = RequestMethod.GET, path = "/ping")
    public ResponseEntity<String> getPing() {
        return ResponseEntity.ok("pong");
    }


}
