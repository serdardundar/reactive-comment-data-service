package io.springguru.reactivecommentdataservice.controller;

import io.springguru.reactivecommentdataservice.model.Comment;
import io.springguru.reactivecommentdataservice.repositories.CommentMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author serdardundar
 * @since 17/12/2018
 */
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentMongoRepository commentMongoRepository;

    @GetMapping(value = "/comments", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Comment> findComments(){
        return commentMongoRepository.findWithTailableCursorBy().delayElements(Duration.ofMillis(500));
    }

    @GetMapping(value = "/comments/{id}")
    Mono<Comment> findById(@PathVariable String id){
        return commentMongoRepository.findCommentById(id);
    }

    @GetMapping("/")
    Mono<String> home(){
        return Mono.just("comments");
    }
}
