package com.ricko.passwordmanager.Controller;


import com.ricko.passwordmanager.Model.User;
import com.ricko.passwordmanager.Repository.UserRepository;
import com.ricko.passwordmanager.Service.EmailService;
import com.ricko.passwordmanager.Service.SecurityService;
import com.ricko.passwordmanager.Service.UserService;
import com.ricko.passwordmanager.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
//@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "/user/registration";
    }

   /* @Autowired
    UserRepository userRepo;*/

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, RedirectAttributes redir, HttpServletRequest request) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/registration";
        }

        userForm.setConfirmationToken(UUID.randomUUID().toString());
        userForm.setEnabled(false);
        userService.save(userForm);
        //securityService.autoLogin(userForm.getUsername(),userForm.getPasswordConfirm());

        SimpleMailMessage registrationEmail = emailService.createEmailContent(userForm, request);
        emailService.sendEmail(registrationEmail);

        redir.addFlashAttribute(
                "confirmationMessage", "A confirmation e-mail has been sent to " + userForm.getEmail());
        return "redirect:/login";
        //return "/user/login";
    }

    /*/login POST controller, it is provided by Spring Security so we dont need to write this*/
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username or password is invalid");
        if (logout != null)
            model.addAttribute("message", "You have been logged out succesfully");
        return "/user/login";
    }


    //@RequestMapping( value = "/confirm", method = RequestMethod.GET )
    @GetMapping("/confirm")
    public String showConfirmationPage(Model model, @RequestParam("token") String token) {
        User user = userService.findByConfirmationToken(token);
        //registerValidator.checkTokenAndSetMessage( modelAndView, user );

        if (user == null) {
            //modelAndView.addObject("invalidToken", "You opened invalid confirmation link. ");
            model.addAttribute("invalidToken", "You opened invalid confirmation link. ");
        } else {
            user.setEnabled(true);
            user.setConfirmationToken(null);
            user.setName("GÓWNOTESTZMIENSIESZMATO");

            if (user.isEnabled() == true)
                model.addAttribute("confirmed", "Your account has been activated seccessfully, you can sign in now.");
            else
                model.addAttribute("confirmed","gówno");
        }

        return "/user/login";
        //modelAndView.setViewName( "/login" );
        //return modelAndView;
    }

    @PostMapping("/loginCheckpoint")
    public String check() {

        return "";
    }

    /*@GetMapping({"/","hello"})
    public String welcome(Model model){
        return "welcome";
    }*/


}
