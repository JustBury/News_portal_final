package by.bury.main.dao;

import by.bury.main.entity.News;

import java.util.List;

public interface NewsDAO {

    public void saveNews(News news);

    public News getNews(int id);

    public List<News> getNewses();

    public void delete(int id);
}
