package com.backendforthehindu.the_hindu_backend.dao;

import com.backendforthehindu.the_hindu_backend.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDAO extends CrudRepository<News, Integer> {

    @Query("Select n From News n where n.category_id=:cat_id or n.content like(:q) Order By n.posted_at DESC")
    public Iterable<News> getNewsByCatQuery(@Param("cat_id") int cat_id, @Param("q") String q);

    @Query("Select n From News n Order By n.posted_at DESC")
    Iterable<News> findAllByTime();

    @Query("Select n From News n Where n.category_id = :cat_id")
    Iterable<News> findAllWithCategory(@Param("cat_id") int cat_id);

}