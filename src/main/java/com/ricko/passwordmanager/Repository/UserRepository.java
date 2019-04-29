package com.ricko.passwordmanager.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*CrudRepository<typ obiektu,typ id>*/
public interface UserRepository extends CrudRepository<User,Integer> {

    //UTWORZENIE WŁASNEJ METODY - wyszukiwania po nazwisku czy loginie
    User findByLogin(String login);
    List<User> findBySurname(String surname);

}
