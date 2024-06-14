package com.lesson2.Less2.Kontroleri;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoKontroler {


    @GetMapping("/demo")
    public String demo()
    {
        return "Demo";
    }


}
