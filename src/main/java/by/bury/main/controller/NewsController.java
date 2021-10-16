package by.bury.main.controller;

import by.bury.main.entity.Comment;
import by.bury.main.entity.News;
import by.bury.main.service.CommentService;
import by.bury.main.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class NewsController {

    private final static String NEWS = "news";
    private final static String NEWSES = "newses";
    private final static String NEWS_ID = "newsId";
    private final static String COMMENT_ID = "commentId";
    private final static String COMMENT = "comment";


    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/")
    public String showHome(Model model) {
        List<News> newses = newsService.getNewses();
        model.addAttribute(NEWSES, newses);
        return "main-page";
    }

    @RequestMapping("/getNews/{id}")
    public String getNews(@PathVariable int id, Model model) {
        News news = newsService.getNews(id);
        Comment comment = new Comment();
        model.addAttribute(NEWS, news);
        model.addAttribute(COMMENT, comment);
        return "news-content";
    }

    @RequestMapping("/addNewNews")
    public String addNewNews(Model model) {
        News news = new News();
        model.addAttribute(NEWS, news);
        return "news-info";
    }

    @PostMapping("/saveNews")
    public String saveNews(@Valid @ModelAttribute(NEWS) News news, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "news-info";
        } else {
            newsService.saveNews(news);
            return "redirect:/";
        }
    }

    @RequestMapping("/updateNews")
    public String updateEmployee(@RequestParam(NEWS_ID) int id, Model model) {
        News news = newsService.getNews(id);
        System.out.println(news.getComments());
        model.addAttribute(NEWS, news);
        return "news-info";
    }

    @RequestMapping("/deleteNews")
    public String deleteNews(@RequestParam(NEWS_ID) int id) {
        newsService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/saveComment")
    public String saveComment(@Valid @ModelAttribute(COMMENT) Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/getNews/" + comment.getIdNews();
        }
        System.out.println(comment.getIdNews());
        commentService.saveComment(comment);
        return "redirect:/getNews/" + comment.getIdNews();
    }

    @RequestMapping("/deleteComment")
    public String deleteComment(@RequestParam(COMMENT_ID) int idComment, @RequestParam(NEWS_ID) int idNews) {
        commentService.deleteComment(idComment);
        return "redirect:/getNews/" + idNews;
    }
}










