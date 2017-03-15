package top.oahnus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by oahnus on 2017/2/22.
 * 22:44
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    @ResponseBody
    public String welcome() {
        return "Welcome To Just";
    }
}
