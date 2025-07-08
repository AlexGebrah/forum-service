package telran.java58.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.forum.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByAuthorIgnoreCase(String author);
    List<Post> findByTagsIn(List<String> tags);
    List<Post> findByDateCreatedBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
}
