package telran.java58.forum.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "posts")
public class Post {
    private String id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    private LocalDateTime dateCreated;
    private final List<String> tags= new ArrayList<>();
    private int likes;
    private final List<Comment> comments = new ArrayList<>();

    public Post (String title, String content, String author, List<String> tags) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
        this.tags.addAll(tags);
    }

    public Post(String title, String content, String author) {
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void like() {
        likes++;
    }

}
