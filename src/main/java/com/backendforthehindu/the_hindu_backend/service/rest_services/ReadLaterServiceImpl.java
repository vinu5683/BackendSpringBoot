package com.backendforthehindu.the_hindu_backend.service.rest_services;

import com.backendforthehindu.the_hindu_backend.dao.ReadLaterDAO;
import com.backendforthehindu.the_hindu_backend.entity.ReadLater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReadLaterServiceImpl implements ReadLaterService {

    @Autowired
    ReadLaterDAO readLaterDAO;

    @Override
    @Transactional
    public void saveNewsById(int uid, int id) {
        ReadLater readLater = new ReadLater(id, uid);
        try {
            readLaterDAO.save(readLater);
            System.out.println("Saved" + id);
        } catch (Exception e) {
            System.out.println("Couldn't Saved " + e);
        }
    }

    @Override
    @Transactional
    public boolean isThisNewsPresent(int nid, int uid) {
        if (readLaterDAO.isExist(nid, uid) >= 1) {
            return true;
        } else return false;
    }

    @Override
    @Transactional
    public Iterable<ReadLater> findByUserId(int uid) {
        return readLaterDAO.findByUserId(uid);
    }

    @Override
    @Transactional
    public void deleteSavedEntry(int userid, int newsid) {
        readLaterDAO.deleteByNews(userid, newsid);
        System.out.println("Deleted " + newsid);
    }
}
