package com.ricko.passwordmanager.Service;

import com.ricko.passwordmanager.Model.Data;
import com.ricko.passwordmanager.Model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    User findByConfirmationToken(String token);

    void currentUserAddData(String site, String password) throws Exception;

    List<Data> getCurrentUserData();


}
