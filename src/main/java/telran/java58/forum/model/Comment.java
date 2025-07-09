package telran.java58.forum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private int likes;
}
