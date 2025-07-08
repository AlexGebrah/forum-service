package telran.java58.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import telran.java58.forum.dto.CommentDto;
import telran.java58.forum.dto.PostCredentialDto;
import telran.java58.forum.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    @Override
    public PostDto addPost(PostCredentialDto post) {
        return null;
    }

    @Override
    public PostDto findPostById(String id) {
        return null;
    }

    @Override
    public void addLike(String id) {

    }

    @Override
    public List<PostDto> findPostByAuthor(String author) {
        return List.of();
    }

    @Override
    public PostDto addComment(String id, CommentDto comment) {
        return null;
    }

    @Override
    public PostDto deletePost(String id) {
        return null;
    }

    @Override
    public List<PostDto> findPostsByTags(List<String> tags) {
        return List.of();
    }

    @Override
    public List<PostDto> findPostsByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return List.of();
    }

    @Override
    public PostDto updatePost(String id, PostCredentialDto post) {
        return null;
    }
}
