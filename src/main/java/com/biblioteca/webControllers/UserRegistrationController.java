package com.biblioteca.webControllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.User;
import com.biblioteca.services.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }
    
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
        BindingResult result) {

        User existing = userService.findByEmail(userDto.getEmail());
        User existing1 = userService.findByEmail(userDto.getFirstName());
        if (existing != null) {
            result.rejectValue("email", null, "Ja Existe uma conta registada com esse email");
        }
        
        if (existing1 != null) {
            result.rejectValue("email", null, "Ja Existe uma conta registada com esse nome");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/registration?success";
    }
	
}
