package com.backendforthehindu.the_hindu_backend.dao;

import com.backendforthehindu.the_hindu_backend.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends CrudRepository<Users, Integer> {
    @Query("select u FROM Users u where u.email=:user")
    Iterable<Users> findByName(@Param("user") String user);

    @Query("Select Count(*) from Users u where u.email=:e and u.password=:p")
    int findUserByEmailPassword(@Param("e") String e, @Param("p") String p);
}
