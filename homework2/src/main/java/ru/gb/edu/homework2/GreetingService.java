package ru.gb.edu.homework2;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public GreetingService() {
        System.out.println("Создана служба");

    }

    public String greeting() {
        return "Привет, юзер!";
    }

    public String question() {
        return "Задавай вопрос";
    }

}
