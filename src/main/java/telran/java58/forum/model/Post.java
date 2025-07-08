package telran.java58.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java58.forum.dto.CommentDto;

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
    private final List<CommentDto> comments = new ArrayList<>();

    public Post (String title, String content, String author, List<String> tags) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
        this.tags.addAll(tags);
    }

    public void addComment(CommentDto comment) {
        comments.add(comment);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public  void like() {
        likes++;
    }

}
