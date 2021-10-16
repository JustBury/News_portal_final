package by.bury.main.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comment" )
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE_INVALID_CONTENT = "The text can be from 1 to 300 characters long";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = MESSAGE_INVALID_CONTENT)
    @Pattern(regexp = "(.){1,300}", message = MESSAGE_INVALID_CONTENT)
    @Column(name = "content")
    private String content;

    @Column(name = "publish_date",updatable = false)

    @CreationTimestamp
    private Date publishDate;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_News")
    private News news;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment сomment = (Comment) o;
        return id == сomment.id
                && Objects.equals(content, сomment.content)
                && Objects.equals(publishDate, сomment.publishDate);
    }

    @Override
    public int hashCode() {
        final int prime = 43;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@'
                +  " Id=" + id +
                ", content='" + content +
                ", publishDate=" + publishDate ;
    }
}
