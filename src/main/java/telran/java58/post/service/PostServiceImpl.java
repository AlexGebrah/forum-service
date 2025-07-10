package telran.java58.post.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import telran.java58.post.dao.PostRepository;
import telran.java58.post.dto.NewCommentDto;
import telran.java58.post.dto.NewPostDto;
import telran.java58.post.dto.PostDto;
import telran.java58.post.model.Post;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private  final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto addNewPost(String author, NewPostDto newPostDto) {
        Post post = modelMapper.map(newPostDto, Post.class);
        post.setAuthor(author);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow();
                return modelMapper.map(post, PostDto.class) ;
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.addLike();
        postRepository.save(post);

    }

    @Override
    public PostDto updatePost(String id, NewPostDto newPostDto) {
        return null;
    }

    @Override
    public PostDto deletePost(String id) {
        return null;
    }

    @Override
    public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostsByAuthor(String author) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostsByTags(List<String> tags) {
        return null;
    }

    @Override
    public Iterable<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }
}
