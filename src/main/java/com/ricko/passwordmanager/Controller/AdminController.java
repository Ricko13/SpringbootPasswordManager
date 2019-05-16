package com.ricko.passwordmanager.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricko.passwordmanager.Repository.DataRepository;
import com.ricko.passwordmanager.Model.User;
import com.ricko.passwordmanager.Repository.UserRepository;
import com.ricko.passwordmanager.Service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /*zamiast robić prywatną zmienną można przy metodzie dodać @Autowired i przekazać obiekt w argumencie*/

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DataRepository dataRepo;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserRepository userService;

    /*Do obsługi JSONa*/
    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("username",securityService.findLoggedInUsername());
        return "hello";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){

        String username=securityService.findLoggedInUsername();
        if(username==null)
            return "kurwa jebana null";

        //return securityService.getLoggedInUser().getUsername();
        return username;
    }

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


    /********************************************************************************** OLD*/

    @GetMapping("/json")
    @ResponseBody
    public String getUsers() throws JsonProcessingException {
        //StringBuilder sb=new StringBuilder();
        /*for(User user:userRepo.findAll()){
            sb.append(user.toString());
        }*/
        return objectMapper.writeValueAsString(userRepo.findAll());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String getUserById(@PathVariable int id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userRepo.findById(id));
    }


    @GetMapping("/{id}/data")
    @ResponseBody
    public String getDataByUser(@PathVariable int  id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Optional<User> user=userRepo.findById(id);

        User user=userRepo.findById(id).orElse(null);
        if(user!=null)
            return objectMapper.writeValueAsString(user.getData());
        else
            return "No user with id="+id;
    }



    /*bardzo stara wersja testowa dodawania danych*/
    @PostMapping("/data/add")
    @ResponseBody
    public String addData(@RequestParam String site,@RequestParam String password){
        // Optional<User> user=userRepo.findById(1);
        // dataRepo.save(new Data(site,password,userRepo.findById(1)));

        //String result;
        /*userRepo.findById(1).ifPresent(
                customer -> dataRepo.save(new Data(site,password,customer))
        );*/

        return "dodano";
    }
}
