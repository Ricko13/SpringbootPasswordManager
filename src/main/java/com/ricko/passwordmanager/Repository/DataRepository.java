package com.ricko.passwordmanager.Repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called dataRepository
// CRUD refers Create, Read, Update, Delete
public interface DataRepository extends CrudRepository<Data, Integer> {
    //roznica miedzy JpaRepository a CrudRepository

    //List<Data> findDataByUserId(int userId);
}
