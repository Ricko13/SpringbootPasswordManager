package com.ricko.passwordmanager.Controller;

import com.ricko.passwordmanager.Repository.Data;
import com.ricko.passwordmanager.Repository.DataRepository;
import com.ricko.passwordmanager.Repository.User;
import com.ricko.passwordmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DataRepository dataRepo;

    @PostMapping(path="/user/add")// Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String login, @RequestParam String password){
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        User user=new User(name,surname,email,login,password);
        userRepo.save(user);
        return "User saved";
    }
//    @PostMapping or @GetMapping are shortcuts for @RequestMapping(method="")
//      @RequestMapping maps all HTTP operations by default

    @GetMapping(path="/user/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/register")
    public String register(){
        return "user/userForm";
    }

    /*@GetMapping("/login")
    public String login(){
        return "TESTlogin";
    }

    @PostMapping(path="/login")
    @ResponseBody
    public String login(@RequestParam String login, @RequestParam String password){

        return ""+login+" "+password;
    }*/



    @GetMapping("/data/add")
    public String dataForm(){
        return "data/dataForm";
    }


    @PostMapping("/data/add")
    @ResponseBody
    public String addData(@RequestParam String site,@RequestParam String password){
       // Optional<User> user=userRepo.findById(1);
       // dataRepo.save(new Data(site,password,userRepo.findById(1)));

        String result;
        userRepo.findById(1).ifPresent(
                customer -> dataRepo.save(new Data(site,password,customer))
        );

        return "dodano";
    }


}
