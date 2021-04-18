package com.backendforthehindu.the_hindu_backend.dao;

import com.backendforthehindu.the_hindu_backend.entity.ReadLater;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadLaterDAO extends CrudRepository<ReadLater, Integer> {

    @Query("Select count(n) from ReadLater n where n.news_id = :nid and n.uid = :user_id")
    int isExist(@Param("nid") int nid, @Param("user_id") int user_id);

    @Query("Select r from ReadLater r where r.uid = :uid")
    Iterable<ReadLater> findByUserId(@Param("uid") int uid);

    @Modifying
    @Query("Delete FROM ReadLater r where r.news_id = :newsid and r.uid = :userid")
    void deleteByNews(int userid, int newsid);
}
