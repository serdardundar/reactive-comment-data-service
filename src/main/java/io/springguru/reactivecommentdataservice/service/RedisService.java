package io.springguru.reactivecommentdataservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.springguru.reactivecommentdataservice.model.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * @author serdardundar
 * @since 17/12/2018
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RedisService {
    private final ObjectMapper objectMapper;
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public Mono<Comment> save(String subscriber, Comment comment) {
        try {
            return reactiveRedisTemplate.opsForSet().add(subscriber, objectMapper.writeValueAsString(comment))
                    .map(any -> comment);
        } catch (Exception e) {
            return Mono.error(new RuntimeException(e));
        }
    }

    public Comment getCommentBySubscriber(String subscriber) {

        try {
            return objectMapper.readValue(reactiveRedisTemplate.opsForSet().pop(subscriber).doOnError(throwable -> new IllegalArgumentException()).block(), Comment.class);
        } catch (IOException e) {
            log.error("Parsing on object mapper", e.getMessage());
            return null;
        }
    }
}
