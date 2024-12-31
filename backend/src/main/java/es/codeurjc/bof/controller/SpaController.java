package es.codeurjc.bof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    @GetMapping({ "/new/**/{path:[^\\.]*}", "/{path:new[^\\.]*}","/new/" })
    public String redirect() {
        return "forward:/new/index.html";
    }

}
