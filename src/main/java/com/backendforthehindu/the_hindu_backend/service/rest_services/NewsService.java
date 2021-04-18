package com.backendforthehindu.the_hindu_backend.service.rest_services;

import com.backendforthehindu.the_hindu_backend.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Iterable<News> getNewsWithCatQuery(int id, String q);

    Iterable<News> getAllNews();

    Optional<News> getNewsById(int id);

    Iterable<News> findAllWithCategory(int id);

    Iterable<News> getNewsByCategory(int id, String str);

    Iterable<News> findAllSavedNews(List<Integer> ids);
}
