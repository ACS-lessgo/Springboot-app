package com.acs.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Define our routes (api endpoints)
@RestController
public class HomeController {
    // Mapping --> get(fetch data from db),put(edit data in db),post(create data in db),delete(delete data from db)
    @GetMapping()
    public String homeControllerHandler(){
        return "This is Home ....!!";
    }

    @GetMapping("/home")
    public String homeControllerHandlerNew(){
        return "This is New Home ....!!";
    }
}
