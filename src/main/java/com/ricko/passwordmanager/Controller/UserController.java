package com.ricko.passwordmanager.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricko.passwordmanager.Repository.Data;
import com.ricko.passwordmanager.Repository.User;
import com.ricko.passwordmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ObjectMapper objectMapper; //do obsługi jsona

    @GetMapping("")
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
}
