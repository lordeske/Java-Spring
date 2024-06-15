package com.less3.less3.Kontroler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NasKontorler {


    @GetMapping("/test")
    public String test()
    {
        return "Demo";
    }



}
