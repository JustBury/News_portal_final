package by.bury.main.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "news")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE_INVALID_TITLE = "The \"title\" field cannot be empty or consist only of spaces";
    private static final String MESSAGE_INVALID_BRIEF = "The \"brief\" field cannot be empty or consist only of spaces";
    private static final String MESSAGE_INVALID_CONTENT = "The \"content\" field must be at least 10 characters long";
    private static final String MESSAGE_INVALID_CONTENT_LENGTH = "The \"content\" field must be at least 10 characters long";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotBlank(message = MESSAGE_INVALID_TITLE)
    private String title;
    @NotBlank(message = MESSAGE_INVALID_BRIEF )
    @Column(name = "brief")
    private String brief;
    @NotBlank(message = MESSAGE_INVALID_CONTENT)
    @Size(min = 10, message = MESSAGE_INVALID_CONTENT_LENGTH)
    @Column(name = "content")
    private String content;

    @Column(name = "date_registration", updatable = false)
    @CreationTimestamp
    private Date publishDate;

    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Comment> comments;

    public News() {
    }

    public News(String title, String brief, String content, Date dateRegistration) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.publishDate = dateRegistration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public void setPublishDate(Date dateRegistration) {
        this.publishDate = dateRegistration;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && Objects.equals(title, news.title)
                && Objects.equals(brief, news.brief)
                && Objects.equals(content, news.content)
                && Objects.equals(publishDate, news.publishDate)
                && Objects.equals(comments, news.comments);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((brief == null) ? 0 : brief.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@'
                + " Id: " + id
                + ", title: " + title
                + ", brief: " + brief
                + ", publishDate: " + publishDate;
    }
}
