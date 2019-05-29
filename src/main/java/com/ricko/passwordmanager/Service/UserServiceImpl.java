package com.ricko.passwordmanager.Service;

import com.ricko.passwordmanager.Model.Data;
import com.ricko.passwordmanager.Repository.DataRepository;
import com.ricko.passwordmanager.Repository.RoleRepository;
import com.ricko.passwordmanager.Model.User;
import com.ricko.passwordmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

//public class UserServiceImpl implements UserDetailsService {
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        //user.setRoles(new HashSet<>(roleRepository.findAll())); // ALE CZEMU DAJE TUTAJ WSZYSTKIE ROLE Z BAZY? xd

        userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findByConfirmationToken(String token){ return userRepo.findByConfirmationToken(token); }


    @Autowired
    SecurityService securityService;
    @Autowired
    DataRepository dataRepository;

    public void currentUserAddData(String site, String password) throws Exception {
        User user=securityService.getLoggedInUser();
        if(user!=null)
            dataRepository.save(new Data(site,password,user));
        else
            throw new Exception();
    }

    public List<Data> getCurrentUserData(){
        return securityService.getLoggedInUser().getData();
    }

    /*@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByLogin(username);
        if(user==null) throw new UsernameNotFoundException(username);


        return return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),);;
    }*/
}
