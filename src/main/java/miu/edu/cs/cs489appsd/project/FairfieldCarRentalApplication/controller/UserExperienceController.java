package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = {"/public"})
public class UserExperienceController {
    @GetMapping(value = "/index")
    public String indexing(Model model) {
        model.addAttribute("name", "Thymeleaf");
        return "index";
    }

    @PostMapping(value = "/login")
    public String processLogin() {
        // Authenticate user and perform necessary actions
        // Redirect to homepage if successful
        return "redirect:/home";
    }


    @GetMapping(value = "/signup")
    public String signup(Model model) {
        model.addAttribute("name", "Thymeleaf");
        return "signup";
    }
}
