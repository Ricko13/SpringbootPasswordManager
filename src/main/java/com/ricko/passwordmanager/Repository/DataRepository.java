package com.ricko.passwordmanager.Repository;

import com.ricko.passwordmanager.Model.Data;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called dataRepository
// CRUD refers Create, Read, Update, Delete
public interface DataRepository extends CrudRepository<Data, Integer> {
    //roznica miedzy JpaRepository a CrudRepository

    //List<Data> findDataByUserId(int userId);
}
