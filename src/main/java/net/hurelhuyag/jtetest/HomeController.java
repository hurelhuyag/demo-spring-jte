package net.hurelhuyag.jtetest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/"})
    String index(ModelMap model) {
        model.put("message", new Message("Hello World"));
        return "index";
    }
}
