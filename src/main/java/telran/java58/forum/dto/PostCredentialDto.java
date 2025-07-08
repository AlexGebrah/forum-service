package telran.java58.forum.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCredentialDto {
    private String author;
    private String title;
    private String content;
    private List<String> tags;
}
