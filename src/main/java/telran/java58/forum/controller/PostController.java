package telran.java58.forum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import telran.java58.forum.dto.CommentDto;
import telran.java58.forum.dto.PostCredentialDto;
import telran.java58.forum.dto.PostDto;
import telran.java58.forum.service.PostService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public PostDto addPost(@RequestBody PostCredentialDto post) {
        return postService.addPost(post);
    }

    @GetMapping("/posts/{id}")
    public PostDto findPostById(@PathVariable String id) {
        return postService.findPostById(id);
    }

    @PatchMapping("/posts/{id}/like")
    public void addLike(@PathVariable String id) {
        postService.addLike(id);
    }

    @GetMapping("/posts/author/{author}")
    public List<PostDto> findPostByAuthor(@PathVariable String author) {
        return postService.findPostByAuthor(author);
    }

    @PatchMapping("/posts/{id}/comment")
    public PostDto addComment(@PathVariable String id, @RequestBody CommentDto comment) {
        return postService.addComment(id, comment);
    }

    @DeleteMapping("/posts/{id}")
    public PostDto deletePost(@PathVariable String id) {
        return postService.deletePost(id);
    }

    @GetMapping("/posts/tags")
    public List<PostDto> findPostsByTags(@RequestParam List<String> tags) {
        return postService.findPostsByTags(tags);
    }

    @GetMapping("/posts/period")
    public List<PostDto> findPostsByPeriod(
            @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        LocalDateTime from = dateFrom.atStartOfDay();
        LocalDateTime to = dateTo.atTime(LocalTime.MAX);

        return postService.findPostsByPeriod(from, to);
    }

    @PatchMapping("/posts/{id}")
    public PostDto updatePost(@PathVariable String id, @RequestBody PostCredentialDto post) {
        return postService.updatePost(id, post);
    }
}
