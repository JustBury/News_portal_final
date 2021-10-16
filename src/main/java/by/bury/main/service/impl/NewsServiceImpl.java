package by.bury.main.service.impl;

import by.bury.main.dao.NewsDAO;
import by.bury.main.entity.News;
import by.bury.main.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    @Transactional
    public void saveNews(News news) {
        newsDAO.saveNews(news);
    }

    @Override
    @Transactional
    public News getNews(int id) {
        return newsDAO.getNews(id);
    }

    @Override
    @Transactional
    public List<News> getNewses() {
        return newsDAO.getNewses();
    }

    @Override
    @Transactional
    public void delete(int id) {
        newsDAO.delete(id);
    }
}
