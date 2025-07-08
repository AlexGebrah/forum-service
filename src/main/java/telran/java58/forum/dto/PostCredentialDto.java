package telran.java58.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCredentialDto {
    @Setter
    private String author;
    private String title;
    private String content;
    private List<String> tags;
}
