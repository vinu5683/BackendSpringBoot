package com.backendforthehindu.the_hindu_backend.dao;

import com.backendforthehindu.the_hindu_backend.entity.Category;
import com.backendforthehindu.the_hindu_backend.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends CrudRepository<Category, Integer> {

}