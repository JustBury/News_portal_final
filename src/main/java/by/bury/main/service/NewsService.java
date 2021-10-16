package by.bury.main.service;

import by.bury.main.entity.News;

import java.util.List;

public interface NewsService {

    public void saveNews(News news);

    public News getNews(int id);

    public List<News> getNewses();

    public void delete(int id);
}
