package io.springguru.reactivecommentdataservice.service;

import io.springguru.reactivecommentdataservice.client.WebfluxClient;
import io.springguru.reactivecommentdataservice.model.Comment;
import io.springguru.reactivecommentdataservice.repositories.CommentMongoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author serdardundar
 * @since 12/12/2018
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentMonitorService implements ApplicationListener<ContextRefreshedEvent> {

    private final WebfluxClient webfluxClient;
    private final CommentMongoRepository commentMongoRepository;
    private final RedisService redisService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        webfluxClient.getCommentStream()
                .log("comment-monitor-service")
                .subscribe(comment -> {
                    Mono<Comment> savedCommentMongo = commentMongoRepository.save(comment);
                    Mono<Comment> savedCommentRedis = redisService.save(comment.getSubscriber(),comment);
                    log.info("Created id for mongo db: " + Objects.requireNonNull(savedCommentMongo.block()).getId());
                    log.info("Inserted redis object: " + Objects.requireNonNull(savedCommentRedis.block()).getSubscriber());
                    log.info("Selected redis object: " + redisService.getCommentBySubscriber(comment.getSubscriber()));
                });
    }
}
