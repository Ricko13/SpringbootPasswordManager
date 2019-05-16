package com.ricko.passwordmanager.Repository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import com.ricko.passwordmanager.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*CrudRepository<typ obiektu,typ id>*/
public interface UserRepository extends JpaRepository<User,Integer> {

    //UTWORZENIE WŁASNEJ METODY - wyszukiwania po nazwisku czy loginie
    User findByUsername(String username);                                                   /*WYWALA SIĘ BO MAM WIĘCEJ NIŻ JEDNEGO UŻYTKOWNIKA Z DANYM USERNAME*/  //ALBO GDY NIE MA TAKIEGO UZYTKOWNIKA
    List<User> findBySurname(String surname);

}
