package io.springguru.reactivecommentdataservice.repositories;

import io.springguru.reactivecommentdataservice.model.Comment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author serdardundar
 * @since 12/12/2018
 */
public interface CommentMongoRepository extends ReactiveMongoRepository<Comment, String> {

    @Tailable
    Flux<Comment> findWithTailableCursorBy();

    Mono<Comment> findCommentById(String id);
}
