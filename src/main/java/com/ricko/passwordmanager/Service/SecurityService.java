package com.ricko.passwordmanager.Service;

import com.ricko.passwordmanager.Model.User;
import com.ricko.passwordmanager.Repository.UserRepository;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String login,String password);
    User getLoggedInUser();
}
