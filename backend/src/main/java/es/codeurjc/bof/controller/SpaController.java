package es.codeurjc.bof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    @GetMapping({ "/**/{path:[^\\.]*}", "/{path:[^\\.]*}","/" })
    public String redirect() {
        return "forward:/index.html";
    }

}
