package ru.gb.edu.homework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private final GreetingService greetServ;

    public GreetingController(GreetingService greetingService) {
        this.greetServ = greetingService;
        System.out.println("Создан контроллер");
    }

    @GetMapping(value = "/")
    public String greeting() {
        return greetServ.greeting();
    }

    @GetMapping(value = "/quest")
    public String question() {
        return greetServ.question();
    }


}
