package edu.mum.cs.onlinehabeshaclothing.controller;

import edu.mum.cs.onlinehabeshaclothing.controller.dto.UserDto;
import edu.mum.cs.onlinehabeshaclothing.model.User;
import edu.mum.cs.onlinehabeshaclothing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto user,
                                      BindingResult result, RedirectAttributes redirectAttributes){

        User existing = userService.findByEmail(user.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()){
            return "register";
        }
        redirectAttributes.addFlashAttribute("successMessage","You've successfully registered to our OnlineHabeshaClothing!");
        userService.save(user);
        return "redirect:/registration";
    }

    @GetMapping("/update/{uid}")
    public String updateProfile(@PathVariable Long uid, Model model){
        model.addAttribute("user", userService.getUser(uid));

        return "redirect:/registration";
    }
    @PostMapping("/delete/{uid}")
    public String deleteProfile(@PathVariable Long uid, Model model){
        userService.deleteUser(uid);
        return "redirect:/registration";
    }
}
