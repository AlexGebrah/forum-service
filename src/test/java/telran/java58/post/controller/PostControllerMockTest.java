package telran.java58.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import telran.java58.post.dto.CommentDto;
import telran.java58.post.dto.NewCommentDto;
import telran.java58.post.dto.NewPostDto;
import telran.java58.post.dto.PostDto;
import telran.java58.post.service.PostService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PostControllerMockTest {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private ObjectMapper objectMapper = new ObjectMapper();

    private PostDto postDto;
    private NewPostDto newPostDto;
    private NewCommentDto newCommentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        
        // Setup test data
        Set<String> tags = new HashSet<>(Arrays.asList("tag1", "tag2"));
        
        newPostDto = new NewPostDto();
        // We can't set fields directly due to lack of setters, so we'll mock the service response instead
        
        newCommentDto = new NewCommentDto();
        // We can't set fields directly due to lack of setters, so we'll mock the service response instead
        
        postDto = PostDto.builder()
                .id("post123")
                .title("Test Post")
                .content("This is a test post")
                .author("testUser")
                .dateCreated(LocalDateTime.now())
                .tags(tags)
                .likes(5)
                .comment(CommentDto.builder()
                        .user("commentUser")
                        .message("Test comment")
                        .dateCreated(LocalDateTime.now())
                        .likes(2)
                        .build())
                .build();
    }

    @Test
    void testAddNewPost() throws Exception {
        when(postService.addNewPost(eq("testUser"), any(NewPostDto.class))).thenReturn(postDto);

        mockMvc.perform(post("/forum/post/testUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPostDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("post123"))
                .andExpect(jsonPath("$.title").value("Test Post"))
                .andExpect(jsonPath("$.author").value("testUser"));
    }

    @Test
    void testFindPostById() throws Exception {
        when(postService.findPostById("post123")).thenReturn(postDto);

        mockMvc.perform(get("/forum/post/post123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("post123"))
                .andExpect(jsonPath("$.title").value("Test Post"))
                .andExpect(jsonPath("$.author").value("testUser"));
    }

    @Test
    void testAddLike() throws Exception {
        mockMvc.perform(patch("/forum/post/post123/like"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUpdatePost() throws Exception {
        when(postService.updatePost(eq("post123"), any(NewPostDto.class))).thenReturn(postDto);

        mockMvc.perform(patch("/forum/post/post123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPostDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("post123"))
                .andExpect(jsonPath("$.title").value("Test Post"));
    }

    @Test
    void testDeletePost() throws Exception {
        when(postService.deletePost("post123")).thenReturn(postDto);

        mockMvc.perform(delete("/forum/post/post123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("post123"));
    }

    @Test
    void testAddComment() throws Exception {
        when(postService.addComment(eq("post123"), eq("commentUser"), any(NewCommentDto.class))).thenReturn(postDto);

        mockMvc.perform(patch("/forum/post/post123/comment/commentUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCommentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("post123"));
    }

    @Test
    void testFindPostsByAuthor() throws Exception {
        List<PostDto> posts = Arrays.asList(postDto);
        when(postService.findPostsByAuthor("testUser")).thenReturn(posts);

        mockMvc.perform(get("/forum/posts/author/testUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("post123"))
                .andExpect(jsonPath("$[0].author").value("testUser"));
    }

    @Test
    void testFindPostsByTags() throws Exception {
        List<PostDto> posts = Arrays.asList(postDto);
        List<String> tags = Arrays.asList("tag1", "tag2");
        when(postService.findPostsByTags(tags)).thenReturn(posts);

        mockMvc.perform(get("/forum/posts/tags")
                .param("values", "tag1", "tag2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("post123"));
    }

    @Test
    void testFindPostsByPeriod() throws Exception {
        List<PostDto> posts = Arrays.asList(postDto);
        LocalDate from = LocalDate.now().minusDays(7);
        LocalDate to = LocalDate.now();
        when(postService.findPostsByPeriod(from, to)).thenReturn(posts);

        mockMvc.perform(get("/forum/posts/period")
                .param("dateFrom", from.toString())
                .param("dateTo", to.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("post123"));
    }
}