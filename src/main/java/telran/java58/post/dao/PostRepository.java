package telran.java58.post.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.post.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
