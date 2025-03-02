package MCM_Project.MCM.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "Welcome Shubham Sharma your are great , run with AWS ";
    }
}
