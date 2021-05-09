package com.example.oauth2tutorial;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private static final String appName = "ThymeleafTour";

   @GetMapping("")
   public String hello(Model model)
    {
        return "index";
    }

   @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {
        model.addAttribute("name", name);
        model.addAttribute("title", appName);
        System.out.println("Model here is - " + model);
        return "home";

    }
}
