package mole.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class RootController {
    @Value("${custom.property}")
    String customTest;


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello() {
        return "hello " + customTest;
    }
}
