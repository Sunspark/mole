package mole.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class RootController {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello() {
        return "hello";
    }
}
