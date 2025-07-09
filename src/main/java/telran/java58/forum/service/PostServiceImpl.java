package telran.java58.forum.service;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import telran.java58.forum.dao.PostRepository;
import telran.java58.forum.dto.CommentDto;
import telran.java58.forum.dto.PostCredentialDto;
import telran.java58.forum.dto.PostDto;
import telran.java58.forum.model.Comment;
import telran.java58.forum.model.Post;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDto addPost(PostCredentialDto post) {
        Post newPost = new Post(post.getTitle(), post.getContent(), post.getAuthor());

        if (post.getTags() != null) {
            post.getTags().forEach(newPost::addTag);
        }
        Post savedPost = postRepository.save(newPost);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow();
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public void addLike(String id) {
        Post post = postRepository.findById(id).orElseThrow();
        synchronized (post) {
            post.like();
            postRepository.save(post);
        }
    }

    @Override
    public List<PostDto> findPostByAuthor(String author) {
        return postRepository.findByAuthorIgnoreCase(author).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    public PostDto addComment(String id, CommentDto commentDto) {
        Post post = postRepository.findById(id).orElseThrow();
        synchronized (post) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            post.addComment(comment);
            Post updated = postRepository.save(post);
            return modelMapper.map(updated, PostDto.class);
        }
    }

    @Override
    public PostDto deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.deleteById(id);
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> findPostsByTags(List<String> tags) {
        return postRepository.findByTagsInIgnoreCase(tags).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    public List<PostDto> findPostsByPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return postRepository.findByDateCreatedBetween(dateFrom, dateTo).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .toList();
    }

    @Override
    public PostDto updatePost(String id, PostCredentialDto postDto) {
        Post post = postRepository.findById(id).orElseThrow();

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        post.getTags().clear();
        if (postDto.getTags() != null) {
            postDto.getTags().forEach(post::addTag);
        }

        Post saved = postRepository.save(post);
        return modelMapper.map(saved, PostDto.class);
    }
}
