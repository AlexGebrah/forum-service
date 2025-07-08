package telran.java58.forum.service;

import telran.java58.forum.dto.CommentDto;
import telran.java58.forum.dto.PostCredentialDto;
import telran.java58.forum.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    PostDto addPost(PostCredentialDto post);

    PostDto findPostById(String id);

    void addLike (String id);

    List<PostDto> findPostByAuthor(String author);

    PostDto addComment(String id, CommentDto comment);

    PostDto deletePost(String id);

    List<PostDto> findPostsByTags(List<String> tags);

    List<PostDto> findPostsByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo);

    PostDto updatePost(String id, PostCredentialDto post);

}
