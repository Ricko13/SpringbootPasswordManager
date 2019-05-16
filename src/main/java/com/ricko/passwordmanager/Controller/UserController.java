package com.ricko.passwordmanager.Controller;


import com.ricko.passwordmanager.Model.User;
import com.ricko.passwordmanager.Service.SecurityService;
import com.ricko.passwordmanager.Service.UserService;
import com.ricko.passwordmanager.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "/user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "/user/registration";
        }
        userService.save(userForm);//to samo działa
        securityService.autoLogin(userForm.getUsername(),userForm.getPasswordConfirm());


        return "redirect:";
        //return "/test";
    }

/*/login POST controller, it is provided by Spring Security so we dont need to write this*/
    @GetMapping("/login")
    public String login(Model model,String error, String logout){
        if(error!=null)
            model.addAttribute("error","Your username or password is invalid");
        if(logout!=null)
            model.addAttribute("message","You have been logged out succesfully");
        return "/user/login";
    }

    /*@GetMapping({"/","hello"})
    public String welcome(Model model){
        return "welcome";
    }*/


}
