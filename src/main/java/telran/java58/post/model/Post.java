package telran.java58.post.model;

import lombok.Singular;
import telran.java58.post.dto.CommentDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Post {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;
    private Set<String> tags;
    private Integer likes;
   private List<Comment> comments;
}
