package com.ricko.passwordmanager.Service;

import com.ricko.passwordmanager.Model.User;
import org.junit.Test;

public class UserServiceImplTest {

    UserService service=new UserServiceImpl();


    @Test
    public void save() {
        User user=new User("name","surname","email","username","password" );


    }

    @Test
    public void findByUsername() {
    }
}