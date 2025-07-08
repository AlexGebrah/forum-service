package telran.java58.forum.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;
    private List<String> tags;
    private int likes;
    private List<CommentDto> comments;
}
