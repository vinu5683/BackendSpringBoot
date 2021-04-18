package com.backendforthehindu.the_hindu_backend.service.rest_services;

import com.backendforthehindu.the_hindu_backend.dao.NewsDAO;
import com.backendforthehindu.the_hindu_backend.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDAO newsDAO;

    @Override
    @Transactional
    public Iterable<News> getNewsWithCatQuery(int id, String q) {
        return newsDAO.getNewsByCatQuery(id, q);
    }

    @Override
    @Transactional
    public Iterable<News> getAllNews() {
        return newsDAO.findAllByTime();
    }

    @Override
    @Transactional
    public Optional<News> getNewsById(int id) {
        return newsDAO.findById(id);
    }

    @Override
    @Transactional
    public Iterable<News> findAllWithCategory(int id) {
        return newsDAO.findAllWithCategory(id);
    }

    @Override
    @Transactional
    public Iterable<News> getNewsByCategory(int id, String str) {
        return newsDAO.getNewsByCatQuery(id, str);
    }

    @Override
    @Transactional
    public Iterable<News> findAllSavedNews(List<Integer> ids) {
        return newsDAO.findAllById(ids);
    }
}
