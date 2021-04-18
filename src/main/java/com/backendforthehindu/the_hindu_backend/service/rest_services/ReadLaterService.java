package com.backendforthehindu.the_hindu_backend.service.rest_services;

import com.backendforthehindu.the_hindu_backend.entity.ReadLater;

public interface ReadLaterService {
    void saveNewsById(int uid,int id);
    boolean isThisNewsPresent(int nid,int uid);

    Iterable<ReadLater> findByUserId(int uid);

    void deleteSavedEntry(int userid, int newsid);
}
